(ns thinktopic.matrix.fressian-test
  (:require [clojure.test :refer :all]
            [clojure.core.matrix :as mat]
            [thinktopic.matrix.fressian :as mf])
  (:import [java.io ByteArrayOutputStream ByteArrayInputStream]))

(mat/set-current-implementation :vectorz)

(deftest a-test
  (testing "Write array to bytes and read back."
    (let [os (ByteArrayOutputStream.)
          data {:info "some info about the data"
                :data (mat/array [[1 2 4] [3 4 5]])}]
      (mf/write-data os data mikera.arrayz.impl.AbstractArray)
      (is (= data
             (mf/read-data (ByteArrayInputStream. (.toByteArray os))))))))
