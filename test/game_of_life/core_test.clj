;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
(ns game-of-life.core-test
  (:require [clojure.test :refer :all]
            [game-of-life.core :refer :all]))

(deftest test-make-board
  (are [height width cells board]
       (= (make-board height width cells) board)
       2 2 #{} [[nil nil] [nil nil]],
       3 2 #{[0 0] [2 1]} [[:on nil]
                           [nil nil]
                           [nil :on]],
       1 4 #{[0 2]} [[nil nil :on nil]]))

(deftest test-neighbors
  (are [height width cell neighbor-cells]
       (= (neighbors height width cell) neighbor-cells)
       4 4 [1 1] [[0 0] [0 1] [0 2]
                  [1 0]       [1 2]
                  [2 0] [2 1] [2 2]],
       5 5 [0 0] [[4 4] [4 0] [4 1]
                  [0 4]       [0 1]
                  [1 4] [1 0] [1 1]],
       4 5 [3 4] [[2 3] [2 4] [2 0]
                  [3 3]       [3 0]
                  [0 3] [0 4] [0 0]]))

(deftest test-step-5x5
  (let [neighbors (make-neighbors-fn 5 5)]
    (are [cells new-cells]
         (= (step neighbors cells) new-cells)

         #{[0 0] [3 3]}
         #{},

         #{[1 0] [1 1] [1 2]}
         #{[0 1] [1 1] [2 1]},

         #{[0 0] [0 1] [1 0] [1 1]}
         #{[0 0] [0 1] [1 0] [1 1]},

         #{[0 0] [0 1]
           [1 0]
                             [2 3]
                       [3 2] [3 3]}
         #{[0 0] [0 1]
           [1 0] [1 1]             [1 4]
                       [2 2] [2 3] [2 4]
                       [3 2] [3 3]
                 [4 1] [4 2]},

         #{      [2 3]
                       [3 4]
           [4 2] [4 3] [4 4]}
         #{[3 2]       [3 4]
                 [4 3] [4 4]
                 [0 3]})))

(deftest test-step-6x6
  (let [neighbors (make-neighbors-fn 6 6)]
    (are [cells new-cells]
         (= (step neighbors cells) new-cells)

         #{[0 0] [3 3]}
         #{},

         #{[1 0] [1 1] [1 2]}
         #{[0 1] [1 1] [2 1]},

         #{[0 0] [0 1] [1 0] [1 1]}
         #{[0 0] [0 1] [1 0] [1 1]},

         #{[0 0] [0 1]
           [1 0]
                             [2 3]
                       [3 2] [3 3]}
         #{[0 0] [0 1]
           [1 0] [1 1]
                       [2 2] [2 3]
                       [3 2] [3 3]},

         #{      [2 3]
                       [3 4]
           [4 2] [4 3] [4 4]}
         #{[3 2]       [3 4]
                 [4 3] [4 4]
                 [5 3]})))
