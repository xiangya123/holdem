(ns poker.pages.index
  "Page for index.

  Signup & login."

  #?@(:node []
      :cljs [(:require
              [re-frame.core  :as re-frame]
              [poker.events.account]
              [poker.subs.account]
              [clojure.string :as str])]))

#?(:clj
     (defn on-signup [])
   :node
     (defn on-signup [])
   :cljs
     (defn on-signup
       [e]
       (let [es     (.. e -target -elements)
             name   (aget es "name" "value")
             avatar (aget es "avatar" "value")
             password (aget es "password" "value")]
         #?(:node nil
            :cljs
              (when-not (or (str/blank? name)
                            (str/blank? avatar)
                            (str/blank? password))
                (re-frame/dispatch [:account/signup
                                    {:player/name   name,
                                     :player/avatar avatar,
                                     :player/password password}])))
         (.preventDefault e))))

#?(:clj
     (defn render-signup-error [])
   :node
     (defn render-signup-error [])
   :cljs
     (defn render-signup-error
       []
       (let [error* (re-frame/subscribe [:account/signup-error])]
         (when @error*
           [:div.text-red-500 @error*]))))

(defn index-page
  []
  [:div.h-screen.w-screen.flex.flex-col.justify-center.items-center
   [:form.flex.flex-col.justify-center.items-center
    {:on-submit on-signup, :suppress-hydration-warning true}
    [:div.leading-9.text-2xl.font-bold.flex.items-center
     "搞起德州破刻!!"]
    [render-signup-error]
    [:div.mt-4
     [:input {:auto-focus true, :name "name", :type "text", :placeholder "账号"}]]
    [:div.mt-4
     [:input {:auto-focus true, :name "password", :type "text", :placeholder "密码"}]]
    [:div.flex.flex-col
     {:style {:font-size "3rem"}}
     [:div.flex
      [:div "🐡" [:input {:type "radio", :name "avatar", :value "🐡"}]]
      [:div "🐠" [:input {:type "radio", :name "avatar", :value "🐠"}]]
      [:div "🐟" [:input {:type "radio", :name "avatar", :value "🐟"}]]]
     [:div.flex
      [:div "🐲" [:input {:type "radio", :name "avatar", :value "🐲"}]]
      [:div "🐴" [:input {:type "radio", :name "avatar", :value "🐴"}]]
      [:div "🐐" [:input {:type "radio", :name "avatar", :value "🐐"}]]]
     [:div.flex
      [:div "🎣" [:input {:type "radio", :name "avatar", :value "🎣"}]]
      [:div "🐈" [:input {:type "radio", :name "avatar", :value "🐈"}]]
      [:div "🐕" [:input {:type "radio", :name "avatar", :value "🐕"}]]]]
    [:button.div.border.border-2.border-black.bg-gray-300.self-stretch.text-center.p-4
     "登陆"]]])

