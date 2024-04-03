import pandas as pd
import ast
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# 데이터 인풋
data = pd.read_csv('./Desktop/study/data/archive/movie_metadata.csv', low_memory=False)

# 데이터 전처리
data = data[['id','genres','vote_average','vote_count','popularity','title','overview']]

# 440위 안에 들어야 하는 최소 투표
# 투표수 비율(m)이 상위 95%여야 440위까지
data['vote_count'] = pd.to_numeric(data['vote_count'], errors='coerce')
m= data['vote_count'].quantile(0.95)
data = data.loc[data['vote_count'] >= m]
print(m)

# 전체 영화 평균 평점 확인
data.loc[:, 'vote_average'] = pd.to_numeric(data['vote_average'], errors='coerce')
C = data['vote_average'].mean()
print(C)

# 최종 스코어 함수
def weighted_rating(x, m=m, C=C):
    v = x['vote_count']
    R = x['vote_average']
    return (v / (v+m) * R) + (m / (m+v) *C)

# score 컬럼에 가중된 스코어로 보정
data['score'] = data.apply(weighted_rating, axis = 1)

# 장르 데이터 띄어쓰기로 이루어진 str로 전처리
data['genres'] = data['genres'].apply(lambda x: ast.literal_eval(x) if isinstance(x, str) else x).apply(lambda x : [d['name'] for d in x]).apply(lambda x: ' '.join(x) if isinstance(x, list) else x)
data.loc[:, 'vote_average'] = pd.to_numeric(data['vote_average'], errors='coerce')
# 인덱스 재설정
data = data.reset_index(drop=True)

# 띄어쓰기로 이루어진 str을 벡터로 변환
count_vector = CountVectorizer(ngram_range=(1,3))
c_vector_genres = count_vector.fit_transform(data['genres'])

# 코사인 유사도 저장
genre_c_sim = cosine_similarity(c_vector_genres, c_vector_genres).argsort()[:,::-1]
print(genre_c_sim)

def get_recommend_movie_list(df, movie_title, top=30):
    target_movie_index = df[df['title'] == movie_title].index.values
    sim_index = genre_c_sim[target_movie_index, :top].reshape(-1)
    sim_index = sim_index[sim_index != target_movie_index]
    result = df.iloc[sim_index].sort_values('score',ascending = False)[:10]
    return result

print(get_recommend_movie_list(data, movie_title='The Dark Knight Rises'))
