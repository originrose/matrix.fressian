(ns thinktopic.matrix.fressian
  (:require [clojure.core.matrix :as mat]
            [fressian-cljs.core :as fressian]
            [fressian-cljs.reader :as freader]
            [fressian-cljs.writer :as fwriter]
            [thinktopic.aljabr.core]))

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
  (let [shape (freader/read-object reader)
        size (apply * shape)
        ary (mat/new-array shape)
        data (thinktopic.aljabr.core/data ary)]
    (dotimes [i size]
      (aset data i (freader/read-double reader)))
    ary))

(def WRITE-HANDLERS
 {thinktopic.aljabr.core/NDArray1float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray2float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray3float64 {ARRAY-TAG array-write-handler}
  thinktopic.aljabr.core/NDArray4float64 {ARRAY-TAG array-write-handler}})

(def READ-HANDLERS
  {ARRAY-TAG array-read-handler})
