(ns mormon-memes.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [mormon-memes.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
