from __future__ import print_function

import numpy as np
import random
import json
import sys
import os
import gensim
from gensim.models import Word2Vec
from filelock import Timeout, FileLock

import networkx as nx
from networkx.readwrite import json_graph
import multiprocessing as mp
from threading import Lock
import pickle as pkl


with open("ann_corpus.txt") as f:
    content = f.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line
sentences = [x.strip() for x in content] 
ann = []
for l in sentences:
        ann.append(l.split());

with open("abstracts.txt") as f:
    content = f.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line
abstracts = [x.strip() for x in content] 
abst = []
for l in abstracts:
        abst.append(l.split());

print("Frases Cargadas")

model = Word2Vec.load("salida")

print("Modelo Incial Cargado")

model.build_vocab(abst, update=True)

print("Comienza el Entrenamiento de Abstracts...")

model.train(abst, total_examples=len(abst), epochs=10)


print("Entrenamiento Terminado.")


model.build_vocab(ann, update=True)

print("Comienza el Entrenamiento de Anotaciones...")

model.train(ann, total_examples=len(ann), epochs=10)


print("Entrenamiento Terminado.")

model.save("version3")
modelWV = model.wv
modelWV.save("version3" + 'kv')


