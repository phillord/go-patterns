(ns go.patterns.chebi
   (:require [tawny.owl :as o]
             [tawny.english :as e]))


(o/defontology chebi-pattern
  :iri "http://s"
  )


(defn exact-synonym [synonym]
  (o/annotation
   (o/annotation
    (o/iri "http://obo-in-owl/exact-synonym")
    synonym)))


(defn biosynthesis-from [o target from]
  (let [term-name target
        from-name from]
    (o/owl-class
     o "bob"
     ;; don't have real go terms here..
     :equivalent
     (o/owl-and "GO_0009058"
            (o/owl-some "has_output" target)
            (o/owl-some "has_input" from))
     :label
     (str term-name " biosynthetic process from " from-name)
     :comment
     ;; should be definition
     (str "The chemical reactions and pathways resulting in the formation of"
          term-name " from " from-name))))


(o/defclass acetyl-CoA)
(o/defclass pyruvate)

(biosynthesis-from chebi-pattern acetyl-CoA pyruvate)

(o/save-ontology "chebi-pattern.omn" :omn)
(o/save-ontology "chebi-pattern.owl" :owl)

