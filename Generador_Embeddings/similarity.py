# -*- coding: utf-8 -*-
"""
Created on Sat May 22 19:22:01 2021

@author: anton
"""

import gensim


if __name__ == '__main__':
    
    print("Introduce el nombre del archivo embeding:")
    
    modelo = str(input())
    
    print("--- Cargando Embedding... ---")
    
    model = gensim.models.Word2Vec.load(modelo)
    vocabulary = set(model.wv.vocab)
    
    print("--- Embedding Cargado ---")
    
    print(modelo)
    
    while True:
        
        print("Introduzca palabra clave para similitud:")
    
        palabra = "<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#" + str(input()) + ">"
        
        result = model.wv.most_similar(positive=[palabra])
        
        for r in result:
            
            print(r)
        
        
        print("Salir? [y/n] [y]")
        
        s = str(input())
        
        if s != "n":
            break
        
