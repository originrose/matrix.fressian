(ns clojure.core.matrix.fressian
  (:require [clojure.core.matrix :as mat]
            [clojure.data.fressian :as fress]
            [clojure.java.io :refer (output-stream input-stream)])
  (:import [org.fressian.handlers WriteHandler ReadHandler ILookup]))

(mat/set-current-implementation :vectorz)

(def ARRAY-TAG "array")

;; tags the array and writes a shape header before all the values
(def array-writer
  (reify WriteHandler
    (write [_ writer array]
      (let [shape (mat/shape array)
            size (apply + shape)]
        ;; write the tag, shape vector, and values
        (.writeTag writer ARRAY-TAG (inc size))
        (.writeObject writer shape)
        (doseq [v (mat/eseq array)]
          (.writeDouble writer v))))))

;; invoked by the "array" tag
(def array-reader
  (reify ReadHandler
    (read [_ reader tag component-count]  ;; see org.fressian.Reader
      (let [shape (vec (.readObject reader))
            size (apply + shape)
            array (mat/zero-array [size])]
        (loop [i 0]
          (when (< i size)
            (mat/mset! array i (.readDouble reader))
            (recur (inc i))))
        (mat/reshape array shape)))))

(def array-write-handlers
  (-> (merge {mikera.arrayz.impl.AbstractArray {ARRAY-TAG array-writer}}
             fress/clojure-write-handlers)
      fress/associative-lookup
      fress/inheritance-lookup))

(def array-read-handlers
  (-> (merge {ARRAY-TAG array-reader} fress/clojure-read-handlers)
      fress/associative-lookup))

(defn write-array
  "Writes the array to an output stream created with clojure.java.io/output-stream.

  A File, URI, URL, Socket, or String (for local path) will all work."
  [x array]
  (with-open [os (output-stream x)]
    (fress/write-object (fress/create-writer os :handlers array-write-handlers) array)))

(defn read-array
  "Reads an array from an input stream created with clojure.java.io/input-stream.

  A File, URI, URL, Socket, byte array, or String (local path) will all work."
  [x]
  (with-open [is (input-stream x)]
    (fress/read-object (fress/create-reader is :handlers array-read-handlers))))


