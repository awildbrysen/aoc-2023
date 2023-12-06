(ns common)

(defn parse-int [n]
  (if (integer? n) n
      (try (Integer/parseInt n)
           (catch Exception _ nil))))