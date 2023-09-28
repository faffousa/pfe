import sys
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem import WordNetLemmatizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# Fonction pour prétraiter le texte
def preprocess_text(text):
    # Tokenization
    tokens = word_tokenize(text.lower())
    
    # Suppression des stopwords
    stop_words = set(stopwords.words('english'))
    tokens = [token for token in tokens if token not in stop_words]
    
    # Lemmatization
    lemmatizer = WordNetLemmatizer()
    tokens = [lemmatizer.lemmatize(token) for token in tokens]
    
    # Reconstruction du texte prétraité
    preprocessed_text = ' '.join(tokens)
    return preprocessed_text

# Fonction pour calculer la similarité cosinus entre deux textes
def calculate_similarity(text1, text2):
    # Prétraitement des textes
    preprocessed_text1 = preprocess_text(text1)
    preprocessed_text2 = preprocess_text(text2)
    
    # Vectorisation des textes prétraités
    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform([preprocessed_text1, preprocessed_text2])

    # Calcul de la similarité cosinus
    similarity_score = cosine_similarity(tfidf_matrix[0], tfidf_matrix[1])[0][0]
    return str(similarity_score)


text1 = sys.argv[1]
text2 = sys.argv[2]
similarity_score = calculate_similarity(text1, text2)
print(similarity_score)