from flask import Flask, jsonify, request
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
import json
import random

app = Flask(__name__)

# data = []
data_wish_list = []
data_other_list = []
# 추천 함수 정의
def get_recommendations(data):
    for item in data:
        if item["wishList"] == 1:
            data_wish_list.append(item)
        else:
            data_other_list.append(item)

    print("Data with wish_list = 1:", data_wish_list)
    print("Data with wish_list != 1:", data_other_list)
    
    # 특성 추출을 위한 TF-IDF 객체 생성
    tfidf = TfidfVectorizer(stop_words='english')
    
    #  TF-IDF 매트릭스 계산
    tfidf_matrix = tfidf.fit_transform([item['title'] + ' ' + item['content'] for item in data_other_list])

    recommended_jobs = []
    # for item in data:
    random_index = random.randint(0, len(data_wish_list) - 1)
    selected_data = data_wish_list[random_index]

    title = selected_data['title']
    content = selected_data['content']

    # query = data['title'] + " " + data['content']
    query = title + " " + content
    query_vector = tfidf.transform([query])

        # 코사인 유사도 계산
    cosine_similarities = linear_kernel(query_vector, tfidf_matrix).flatten()

        # 유사도가 높은 순으로 인덱스 정렬
    similar_indices = cosine_similarities.argsort()[::-1]

        # 상위 10개 추천
    recommended_jobs.extend([data[i] for i in similar_indices[:10]])

    return recommended_jobs


# 추천 API 라우트 설정
@app.route('/recommend', methods=['POST'])
def recommend():

    # 요청 확인 로그
    print("Received recommendation request")

    request_data = json.loads(request.data)
    recommended_jobs = get_recommendations(request_data)
    
    print(request_data)

    # 응답 확인 로그
    print("Sending recommended jobs response")
    return jsonify(recommended_jobs)

if __name__ == '__main__':
    app.run(port=5000, debug=True)
