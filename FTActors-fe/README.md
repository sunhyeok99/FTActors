# Vue 복습

* v-bind : 이중 중괄호 구문은 여닫는 HTML 태그에 대해서만 사용할 수 있기 때문에, 속성값에 넣기 위해서는 v-bind구문을 이용해야 함

(ex)
```js
v-bind:href=”vueLink”
:href=”vueLink”
```
* v-on : 콜론 뒤에 수신할 이벤트 취함

(ex)
```js
v-on:click=”counter++”
@click=”counter++”
```