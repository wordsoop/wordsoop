(ns mormon-memes.views
    (:require [re-frame.core :as re-frame]
              [garden.core :refer [css]]
              [garden.selectors :refer [descendant]])
    (:require-macros
     [garden.selectors :refer [defclass defselector defpseudoelement defpseudoclass]]))

(enable-console-print!)

(def img-width 360)

(defclass grid-item) ; blocks in the grid

(defclass on-hover) ; hover
(defclass off-hover)
(defclass hoverable)
(defpseudoclass hover)
(defpseudoelement before)

(def grid-css
  (css
   #_[grid-item {:margin-bottom "1em"}]
   [(descendant grid-item :img) {:width (str img-width "px")}]))

(def hover-css
  (css [(descendant hoverable on-hover) {:display "none"}]
       [(descendant (hoverable hover) off-hover) {:display "none"}]
       [(descendant (hoverable hover) on-hover) {:display "block"}]))



(def imgs [
           "http://i.imgur.com/UT9TgTn.jpg"
           "http://i.imgur.com/kUQqEiB.jpg"
           "http://i.imgur.com/P4fzT78.jpg"
           "http://i.imgur.com/eX7MDUm.jpg"
           "http://i.imgur.com/rxHUchE.jpg"
           "http://i.imgur.com/zr2HRJi.jpg"
           "http://i.imgur.com/TtfSN6q.jpg"
           "http://i.imgur.com/5uWhnBx.jpg"
           "http://i.imgur.com/r3qeozl.jpg"
           "http://i.imgur.com/RoJ9ezh.jpg"
           "http://i.imgur.com/SvNnr1b.jpg"
           "http://i.imgur.com/1hLp8BG.jpg"
           "http://i.imgur.com/1FhinCS.jpg"
           "http://i.imgur.com/D3YB24W.jpg"

           "http://i.imgur.com/wddOklO.png"
           "http://i.imgur.com/OlK7XZ9.png"
           "http://i.imgur.com/Vjuc5wi.png"
           "http://i.imgur.com/JmKJzX9.png"
           "http://i.imgur.com/qUlU3Rq.png"
           "http://i.imgur.com/D5gm7N4.png"
           "http://i.imgur.com/dxghNQn.png"
           "http://i.imgur.com/JYjZZvZ.png"
           "http://i.imgur.com/McUV70H.png"
           "http://i.imgur.com/Jx4JsR9.png"
           "http://i.imgur.com/TkDWQ4A.jpg"

           ;; Temple Interview Questions
           "http://i.imgur.com/6dBbcK1.png"
           "http://i.imgur.com/B3Qt0Bs.png"
           "http://i.imgur.com/jLDWhhi.png"
           "http://i.imgur.com/aLnflVk.png"
           
           ])

(def img-path "img/")

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div

       ;; CSS --------------------------------------------------
       [:style ;TODO this is the wrong way to combine styles
        (str grid-css "\n"
             hover-css)]

       
       ;; Header --------------------------------------------------
       [:nav.navbar.navbar-light.bg-faded
        [:div.container
         [:a.navbar-brand
          [:h1
           {:style {:font-family "'Montserrat', sans-serif"
                    :color       "#666"}}
           "Words of our Prophets"]]
         [:small {:style {:position "absolute"
                          :bottom   30
                          :color    "#aaa"}}
          "“Truth never lost ground by enquiry” "
          [:small "-William Penn"]]]]
       [:br]

       
       ;; Notice --------------------------------------------------
       [:div.container
        [:div.row
         [:div.col-md-8.col-md-offset-2
          [:div.panel-group
           [:div.panel.panel-default
            [:div.panel-heading
             #_[:button.btn.btn-warning.pull-right {:data-toggle "collapse"
                                                    :data-target "#basic-info"} "notes"]
             [:h4.panel-title.text-md-center
              [:span
               [:a.brand-warning.disable  {:data-toggle "collapse"
                                           :data-target "#basic-info"}
                "Quickly digestible quotes from LDS Prophets & Apostles "
                [:i.fa.fa-chevron-right {:style {:color "#0275d8"}}]]]]]
            
            [:div#basic-info.panel-collapse.collapse.out
             [:ul.list-group
              [:li.list-group-item "Must come from reputable sources"]
              [:li.list-group-item "Must represent the author/orator's true intent"]
              [:li.list-group-item
               [:span
                "Should inspire us to become "
                [:strong {:style {:color "#0275d8"}} "willing to understand"]
                " our church's truth claims"]]]
             [:span
              [:small "Note: this is a work in progress. Please submit suggestions, memes, support, hate mail, etc. to "
               [:a {:href "mailto:wordsoop@protonmail.com"} "WordsOOP@protonmail.com"]]]]]]]]]
       [:br]

       
       ;; Upload Image --------------------------------------------------
       [:div.container
        [:div.row
         [:div.col-md-8.col-md-offset-2
          [:div.input-group
           [:input.form-control {:disabled    "true"
                                 :type        "text"
                                 :placeholder "Email links to new images (eg upload to Imgur) to WordsOOP@protonmail.com"}]
           [:span.input-group-btn
            [:button.btn.btn-default.btn-success.disabled {:type "button"} "Upload"]]]]]]
       [:br]

       (prn )
       ;; Grid --------------------------------------------------
       [:div.container {:style {:background-color "#eeeeee"}}
        [:br]
        [:div.grid
         (map (fn [x]
                (let [path x #_ (str img-path x)]
                  ^{:key path}
                  [:div.grid-item.hoverable {:style {:position "relative"}}
                   
                   [:div.on-hover
                    {:style {:position   "absolute"
                             :background "rgba(255,255,255,.5)"
                             :width      "100%"
                             :height     "100%"}}
                    [:div.text-md-center {:style {:position "absolute"
                                                  :top      "45%"
                                                  :left     0
                                                  :width    "100%"}}
                     [:div.btn-group
                      [:button.btn.btn-sm.btn-primary.fa.fa-chevron-up.disabled]
                      [:button.btn.btn-sm.btn-primary.fa.fa-chevron-down.disabled]]
                     [:br] [:br]
                     [:input {:on-click #(-> % .-target (.select)) ; click selects all
                              :type     "text"
                              :value    (str #_(-> js/window
                                                   .-location
                                                   .-href)
                                             path)}]]]
                   [:img {:src path}]]))
              (shuffle imgs))]]

       
       ;; Start Masonry - !side effecting 
       (re-frame/dispatch [:setup-masonry])])))
