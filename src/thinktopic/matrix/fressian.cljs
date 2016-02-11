(ns thinktopic.matrix.fressian
  (:require [clojure.core.matrix :as mat]
            [fressian-cljs.core :as fressian]
            [fressian-cljs.reader :as freader]
            [fressian-cljs.writer :as fwriter]
            [thinktopic.aljabr.core]))

(enable-console-print!)

(def ARRAY-TAG "array")

(defn array-write-handler
  [wtr o]
  (let [shape (mat/shape o)
        size (apply * shape)]
    (fwriter/write-tag wtr "array" (inc size))
    (fwriter/write-object wtr shape)
    (doseq [v (mat/eseq o)]
      (fwriter/write-double wtr v))))

(defn array-read-handler
  [reader tag component-count]
  (let [shape (freader/read-object reader)]
    (mat/compute-matrix shape (fn [& _] (freader/read-double reader)))))

; All of the types supported by ndarray lib:
; - :generic
; - :uint8 :uint8-clamped :uint16 :uint32
; - :int8 :int16 :int32
; - :float32 :float64
; Each type gets 4 type definitions.
(def WRITE-HANDLERS
 {thinktopic.aljabr.core/NDArray1float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray2float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray3float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray4float64 {ARRAY-TAG array-write-handler}})

(def READ-HANDLERS
  {ARRAY-TAG array-read-handler})
