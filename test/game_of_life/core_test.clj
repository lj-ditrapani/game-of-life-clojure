;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest game-of-life
  (testing "make-board"
    (are [height width cells board]
         (= (make-board height width cells) board)
         2 2 #{} [[nil nil] [nil nil]],
         3 2 #{[0 0] [2 1]} [[:on nil]
                             [nil nil]
                             [nil :on]]),
         1 4 #{[0 2]} [[nil nil :on nil]])
  (testing "neighbors"
    (is (= (neighbors 4 4 [1 1]) [[0 0] [0 1] [0 2]
                                  [1 0]       [1 2]
                                  [2 0] [2 1] [2 2]]))))
  


;(run-tests)
