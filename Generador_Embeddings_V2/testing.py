import gensim
from collections import Counter

import gensim
from numpy import log10
import pandas as pd
import plotly
import plotly.graph_objs as go
import plotly.express as px
import umap.umap_ as umap
from plotly.offline import download_plotlyjs, init_notebook_mode,  plot
from plotly.graph_objs import *


def setMetadata(file):
    
    dic = dict()
    
    f = open(file, 'r')
    Lines = f.readlines()
    project = ""
 
    count = 0
    # Strips the newline character
    for line in Lines:
        
        line = line.strip()
        if count == 0:
            project = line
            dic[line] = dict()
            count += 1
        elif line == "":
            count = 0
        else:
            line = line.split()
            prop = line[0]
            val = line[1]
            dicProp = dic[project]
            dicProp[prop] = val
            count += 1
        


    f.close()
    
    return dic

def listForProperty(projects, prop, dic):
    
    listP = []
    
    for p in projects:
        dicP = dic[p]
        if prop in dicP.keys():
            listP.append(dicP[prop]);
        else:
            listP.append("EMPTY");
    
    return listP


model = gensim.models.Word2Vec.load("salida")
vocabulary = set(model.wv.vocab)


reducer = umap.UMAP(metric='cosine', n_neighbors=15, min_dist=0.05, random_state=42)
modelV2 = model[[w for w in vocabulary if "<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#PROJECT_ID_" in w]]
embedding = reducer.fit_transform(modelV2)

d = pd.DataFrame(embedding, columns=['c1', 'c2'])
d['project'] = [w.replace('<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#','').replace('>','') for w in vocabulary if "<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#PROJECT_ID_" in w]

dic = setMetadata('projects_metadata.lst')

props = ['SPR.hasLibrary', 'SPR.hasOrganismPart', 'SPR.hasSpecie', 'SPR.hasCellType']

for prop in props:

    filename = "UMAP_" + prop + ".html"
    d[prop] = listForProperty(d['project'], prop, dic)


    fig = px.scatter(d, x="c1", y="c2", color=prop, hover_data=['project'], title="UMAP para proyectos, basado en " + prop,width=800, height=600)
    chart = plotly.offline.plot(fig, filename=filename)



#word = '<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#PROJECT_ID_0127>'
#print("longitud del vocab ", len(vocab))
#if word in model.wv.vocab:
#    print(word,' esta')
#    print(model.wv[word])