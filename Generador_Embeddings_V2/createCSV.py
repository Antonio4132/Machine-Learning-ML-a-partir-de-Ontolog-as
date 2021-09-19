import gensim
from gensim.models import KeyedVectors
import pandas as pd


kv = KeyedVectors.load('version3kv')

projects = []

for idx, key in enumerate(kv.vocab):
    if 'PROJECT_ID_' in key:
        projects.append(key)
        
sort = sorted(projects)


ordered_vocab = [(term.replace('<http://www.semanticweb.org/alicia/ontologies/2020/8/singleCellRepositories#','').replace('>',''), voc.index, voc.count) for term, voc in kv.vocab.items() if 'PROJECT_ID_' in term]
ordered_vocab = sorted(ordered_vocab, key=lambda k: k[0])
ordered_terms, term_indices, term_counts = zip(*ordered_vocab)
word_vectors = pd.DataFrame(kv.syn0[term_indices, :], index=ordered_terms)

word_vectors.to_csv('projects_kvV3.csv')
