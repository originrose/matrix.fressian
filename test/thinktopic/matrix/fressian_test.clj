(ns thinktopic.matrix.fressian-test
  (:require [clojure.test :refer :all]
            [clojure.core.matrix :as mat]
            [thinktopic.matrix.fressian :refer :all])
  (:import [java.io ByteArrayOutputStream ByteArrayInputStream]))

(mat/set-current-implementation :vectorz)

(deftest a-test
  (testing "Write array to bytes and read back."
    (let [os (ByteArrayOutputStream.)
          data {:info "some info about the data"
                :data (mat/array [[1 2] [3 4]])}]
      (write-data os data mikera.arrayz.impl.AbstractArray)
      (is (= data
             (read-data (ByteArrayInputStream. (.toByteArray os))))))))
