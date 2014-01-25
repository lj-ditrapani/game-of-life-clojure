;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
;;; A location is an [x y] coordinate
;;; The state of the world is a set of coordinates representing live
;;; cells
(ns game-of-life.core)

;; Height and width are needed for show-board and
;; neighbors (wrap around)
;; neighbors is needed for step

(defn show-board [height width cells]
  [[nil nil] [nil nil]])

(defn neighbors [height width cell]
  ())

(defn step
  " Preforms one step in the game of life
  Returns a new, updated set of live cells
  cells is a set of coordinates representing live cells"
  [neighbors cells]
  ())

(defn make-show-board-fn [height width]
  (partial show-board height width))

(defn make-neighbors-fn [height width]
  (partial neighbors height width))

(defn make-step-fn [neighbors]
  (partial step neighbors))

(defn init
  ""
  [height width]
  (let [neighbors (make-neighbors-fn height width)]
    [(make-show-board-fn height width)
     neighbors
     (make-step-fn neighbors)]))
