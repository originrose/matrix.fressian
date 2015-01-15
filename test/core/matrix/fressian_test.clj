(ns core.matrix.fressian-test
  (:require [clojure.test :refer :all]
            [clojure.core.matrix :as mat]
            [clojure.core.matrix.fressian :refer :all])
  (:import [java.io ByteArrayOutputStream ByteArrayInputStream]))

(deftest a-test
  (testing "Write array to bytes and read back."
    (let [os (ByteArrayOutputStream.)
          array (mat/array [[1 2] [3 4]])]
      (write-array os array)
      (is (= array
                 (read-array (ByteArrayInputStream. (.toByteArray os))))))))
