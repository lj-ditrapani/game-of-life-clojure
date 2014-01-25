;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
;;; A location is an [x y] coordinate
;;; The state of the world is a set of coordinates representing live
;;; cells
(ns game-of-life.core)

;; Height and width are needed for make-board and
;; neighbors (wrap around)
;; neighbors is needed for step

(defn make-board [height width cells]
  (let [row (vec (repeat width nil))
        board (vec (repeat height row))
        create-cell-at (fn [board cell]
                         (assoc-in board cell :on))]
    (reduce create-cell-at board cells)))

(defn neighbors [height width cell]
  ())

(defn step
  " Preforms one step in the game of life
  Returns a new, updated set of live cells
  cells is a set of coordinates representing live cells"
  [neighbors cells]
  ())

(defn make-make-board-fn [height width]
  (partial make-board height width))

(defn make-neighbors-fn [height width]
  (partial neighbors height width))

(defn make-step-fn [neighbors]
  (partial step neighbors))

(defn init
  ""
  [height width]
  (let [neighbors (make-neighbors-fn height width)]
    [(make-make-board-fn height width)
     neighbors
     (make-step-fn neighbors)]))
