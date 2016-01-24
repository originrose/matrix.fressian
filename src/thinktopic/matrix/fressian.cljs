(ns thinktopic.matrix.fressian
  (:require [clojure.core.matrix :as mat]
            [fressian-cljs.core :as fressian]
            [fressian-cljs.reader :as freader]
            [fressian-cljs.writer :as fwriter]
            [thi.ng.ndarray.core]))

(enable-console-print!)

(def ARRAY-TAG "array")

(defn double-write-handler
  [wtr o]
  (println "writing double array")
  (let [shape (apply list (mat/shape o))
        size (apply + shape)
        dims (count shape)]
    (println "shape: " shape "size: " size "dims: " dims)
    (fwriter/write-tag wtr "array" (+ size 1 dims))
    (fwriter/write-int wtr dims)
    (doseq [d shape]
      (fwriter/write-int wtr d))
    (doseq [v (mat/eseq o)]
      (println "writing: " v (type v))
      (fwriter/write-double wtr v))))

; All of the types supported by ndarray lib:
; - :generic
; - :uint8 :uint8-clamped :uint16 :uint32
; - :int8 :int16 :int32
; - :float32 :float64
; Each type gets 4 type definitions.
(def WRITE-HANDLERS
 {thi.ng.ndarray.core/NDArray1float64 {"array" double-write-handler}
  thi.ng.ndarray.core/NDArray2float64 {"array" double-write-handler}
  thi.ng.ndarray.core/NDArray3float64 {"array" double-write-handler}
  thi.ng.ndarray.core/NDArray4float64 {"array" double-write-handler}})

