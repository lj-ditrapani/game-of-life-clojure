;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest game-of-life
  (testing "show-board"
    (is (= (show-board #{})) [[nil nil] [nil nil]])))
;;  (testing "neighbors"
;    (is (= (neighbors )
  


(run-tests)
