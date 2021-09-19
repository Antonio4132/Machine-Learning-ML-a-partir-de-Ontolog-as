import argparse
import sys
import os
import networkx as nx
import pickle as pkl
import json
import generate_graph as gg
import compute_vectors as cv
from networkx.readwrite import json_graph

if __name__ == '__main__':

    parser = argparse.ArgumentParser(description = "Dl2Vec: it is a tool can convert the complext axioms in the ontology into graph representation")

    parser.add_argument("-ontology", nargs = '?', metavar = "ontology OWL file", type = str,
                     help = "File containing ontology in OWL format",default='')

    parser.add_argument("-associations", nargs="?",  metavar="annotation files ", type=str, help ="annotation files that link to the classes in the ontology")
    parser.add_argument("-outfile", nargs = '?', metavar = "output file", type = str,
                     help = "Output file with ontology embeddings",default='')
    parser.add_argument("-embedsize", nargs = '?', metavar = "embedding size", type = int,default=100,
                     help = "Size of obtained vectors")
    parser.add_argument("-windsize", nargs = '?', metavar = "window size", type = int,default=10,
                     help = "Window size for word2vec model")
    parser.add_argument("-mincount", nargs = '?', metavar = "min count", type = int,default=1,
                     help = "Minimum count value for word2vec model")
    parser.add_argument("-model", nargs = '?', metavar = "model", type = str,default='sg',
                     help = "Preferred word2vec architecture, sg or cbow")
    parser.add_argument("-entity_list", nargs ="?", metavar ="the entity list that needs to generate the embedding", type=str, default="",
                    help =" the entity list in which each entity that need to start random walk and generate the embedding")

    args = parser.parse_args()
    
    ontology_file =args.ontology
    association_file=args.associations
    window=args.windsize
    embedding=args.embedsize
    mincoun=args.mincount
    model=args.model
    outfile =args.outfile
    entity_list =args.entity_list
    
    if (ontology_file == '' ):
    	print ("\nError:Mandatory ontology file missing. For help, run: python runDL2Vec.py --help\n")
    	sys.exit()
    if (association_file == ''):
    	print ("\nError:Mandatory association file missing. For help, run: python runDL2Vec.py --help\n")
    	sys.exit()
    if (outfile == ''):
    	print ("\nError:Mandatory output-file name missing. For help, run: python runDL2Vec.py --help\n")
    	sys.exit()
    if entity_list == "":
        entity_list =association_file
    if (model != 'sg' and model != 'cbow'):
    	model ='sg'
    	 
    commandF ="groovy ProcessOntology.groovy " + str(ontology_file) +" "+"elk2"
    os.system(commandF)

    axioms_file = "axioms.lst"
    instances_file = "instances.lst"
    
    
    #G = gg.generate_graph(axioms_file,instances_file)

    #ºcv.gene_node_vector(G,entity_list,outfile)
