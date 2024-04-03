<template>
  <div class="board-header">
    <h1>BOARD Update</h1>
  </div>
  <div class="board-page">
    <div class="board-list">
      <ul class="list-group list-group-flush">
        <li class="list-group-item update-form">
          <label><b>공고명</b></label>
          <input v-model="editedRecruitment.title" type="text" class="form-control">
        </li>
        <!-- 생략된 다른 항목들 -->
        <li class="list-group-item update-form">
          <label><b>지원마감일자</b></label>
          <input v-model="editedRecruitment.endDate" type="date" class="form-control">
        </li>
        <li class="list-group-item update-form">
          <label for="image">이미지</label>
          <input type="file" id="image" @change="onImageChange" class="input-field" />
          <div v-if="selectedImage" class="image-preview">
            <img :src="selectedImage" v-if="selectedImage" class="preview-img">
            <button @click="clearSelectedImage" class="clear-btn">Clear</button>
          </div>
        </li>
      </ul>
      <button @click="updateRecruitment" class="btn btn-primary submit-btn">수정</button>
    </div>
  </div>
</template>

<style>
.board-header {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

h1 {
  font-size: 2rem; /* Adjusted for better readability */
}

.board-page {
  display: flex;
  justify-content: center;
}

.board-list {
  width: 100%; /* Adjusted to take the full width for better form presentation */
  max-width: 600px; /* Maximum width to ensure form does not stretch too far on larger screens */
}

.update-form {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.update-form label {
  margin-bottom: 5px;
}

.image-preview {
  display: flex;
  align-items: center;
}

.preview-img {
  max-height: 100px; /* Limit image preview size */
  margin-right: 10px;
}

.clear-btn {
  cursor: pointer;
  background-color: #f44336; /* Red color for clear button */
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
}

.submit-btn {
  width: 100%;
  padding: 10px 0;
}

.input-field {
  margin-bottom: 10px;
}
</style>
