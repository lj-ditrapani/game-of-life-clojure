;;; Author:  Lyall Jonathan Di Trapani ---------------------------------
;;; A live cell is a 2-D location represented by an [x y] coordinate
;;; The state of the world is a set of coordinates representing live
;;; cells
(ns game-of-life.core)

;; Height and width are needed for make-board and
;; neighbors (wrap around)
;; neighbors is needed for step

(defn make-board
  "Returns a vector of vectors representing a 2-D board.
  Dead cells are represented by nil entries and
  live cells by the :on keyword."
  [height width cells]
  (let [row (vec (repeat width nil))
        board (vec (repeat height row))
        create-cell-at (fn [board cell]
                         (assoc-in board cell :on))]
    (reduce create-cell-at board cells)))

(defn neighbors
  "Returns the 8 neighbors of cell from a board with given height and
  width.  The board is assumed to be toroidal (the top and bottom of
  the board wrap around as well as the left with the right.)"
  [height width [x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= 0 dx dy)]
    (let [new-x (mod (+ x dx) height)
          new-y (mod (+ y dy) width)]
      [new-x new-y])))

(defn step
  "Performs one step in the game of life.
  Returns a new, updated set of live cells.
  'cells' is a set of 2-D coordinates representing live cells
  'neighbors' is the neighborhood function used to compute the
  neighborhood of live cells."
  [neighbors cells]
  (let [loc-2-cell-count (frequencies (mapcat neighbors cells))
        live? (fn [[loc cell-count]]
                (or (= cell-count 3)
                    (and (cells loc) (= cell-count 2))))
        get-loc (fn [[loc cell-count]] loc)]
    (set (map get-loc (filter live? loc-2-cell-count)))))

(defn make-make-board-fn [height width]
  (partial make-board height width))

(defn make-neighbors-fn [height width]
  (partial neighbors height width))

(defn make-step-fn [neighbors]
  (partial step neighbors))

(defn init
  "Returns partial functions closed over height and width"
  [height width]
  (let [neighbors (make-neighbors-fn height width)]
    [(make-make-board-fn height width)
     neighbors
     (make-step-fn neighbors)]))

(defn print-board [board]
  (doseq [row board]
    (println (apply str (map #(if % \O \-) row))))
  (println ""))

(defn run [height width steps cells]
  (let [[make-board neighbors step] (init height width)
        live-cells-seq (take steps (iterate step cells))]
    (dorun steps (map #(print-board (make-board %)) live-cells-seq))))

;; Pulsar
(run 6 6 3 #{[1 0] [1 1] [1 2]})
;; Pulsar
(run 6 6 3 #{[0 0] [0 1]
             [1 0]
                               [2 3]
                         [3 2] [3 3]})
;; Glider
(run 6 6 18 #{      [2 3]
                         [3 4]
             [4 2] [4 3] [4 4]})
