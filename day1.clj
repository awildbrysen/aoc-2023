(ns day1
  (:require [clojure.string :as s]))

(defn parse-int [n]
  (if (integer? n) n
      (try (Integer/parseInt n)
           (catch Exception _ nil))))

(defn calculate-calibration-value [input]
  (let [digits (->> (s/split input #"")
                    (map #(parse-int %))
                    (filter #(some? %))
                    (map str))]
    (reduce str [(first digits) (last digits)])))

(defn count-calibration-values [list]
  (->> list
       (map calculate-calibration-value)
       (map parse-int)
       (filter some?)
       (reduce +)))

(defn replace-from-map [replacements input]
  (let [tmp (atom input)]
    (doseq [[rk rv] replacements]
      (reset! tmp (s/replace @tmp (name rk) rv)))
    @tmp))

(comment
  ; part 1
  (->> [["1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"]
        (s/split (slurp "day1.txt") #"\r\n")]
       (map count-calibration-values))

  ; part 2
  (->> [["two1nine" "eightwothree" "abcone2threexyz" "xtwone3four" "4nineeightseven2" "zoneight234" "7pqrstsixteen"]
        (s/split (slurp "day1.txt") #"\r\n")]
       (map (fn [in] (map #(replace-from-map {:one "o1e" :two "t2o" :three "t3e" :four "f4r" :five "f5e" :six "s6x" :seven "s7n" :eight "e8t" :nine "n9n"} %) in)))
       (map count-calibration-values)))


