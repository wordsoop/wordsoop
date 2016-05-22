(ns mormon-memes.handlers
    (:require [re-frame.core :as re-frame]
              [mormon-memes.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

;; detects when images are loaded and runs a function
;; (def images-loaded (js/imagesLoaded ".grid" (fn [] (prn "image popped in!"))))

(re-frame/register-handler
 :setup-masonry
 (fn [_ db]
   (do
     ;; responsive version ----------
     (let [msnry (js/Masonry. ".grid" (clj->js {:itemSelector ".grid-item"
                                                :columnWidth  ".grid-item"
                                                :percentPosition true}))]
       (-> (js/imagesLoaded ".grid")
           (.on "progress" (fn [instance image] (js/Masonry. ".grid" "layout")))))
     ;; non-responsive version ----------
     #_(let [msnry (js/Masonry. ".grid" (clj->js {:itemSelector ".grid-item"
                                                :columnWidth  120}))]
       (-> (js/imagesLoaded ".grid")
           (.on "progress" (fn [instance image] (js/Masonry. ".grid" "layout")))))
     db)))
