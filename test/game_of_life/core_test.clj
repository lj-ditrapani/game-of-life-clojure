;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest game-of-life
  (testing "show-board"
    (is (= (show-board 2 2 #{})) [[nil nil] [nil nil]]))
  (testing "neighbors"
    (is (= (neighbors 4 4 [1 1]) [[0 0] [0 1] [0 2]
                                  [1 0]       [1 2]
                                  [2 0] [2 1] [2 2]]))))
  


;(run-tests)
