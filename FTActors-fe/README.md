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

* @submit.prevent=”submitForm” : 버튼을 클릭할 때 페이지가 새로 고침 됨 

    (서버가 없이 로컬 환경에서 실행하는데도 여전히 브라우저가 로컬 기기로 HTTP 요청을 보냄  >  브라우저가 요청을 자동으로 전송하는 기본값을 방지할 필요성) > event.preventDefault로도 할 수 있음

* @keyup.enter="confirmInput” : enter 키를 눌렀다가 뗄 때 트리거되는 이벤트

* v-once : 값을 처음 한 번만 평가. 값이 바뀌어도 반영하지 않음

* v-model : vue의 이 디렉티브가 가 관리할 데이터 프로퍼티 지정 > 양방향 바인딩에 사용 = v-bind:value v-on:input 의 축약어

    입력 이벤트를 수신하는 동시에 value값을 입력 요소에 다시 보냄 (데이터 바인딩과 이벤트 처리의 결합)

(ex)
```js
<input type=”text” v-model=”name”>

- 이벤트에 바인딩되지 않은 모든 메서드는 화면에 변경이 생길 때마다  Vue에 의해 재실행 (사용되지 않더라도)
- computed : 메서드처럼 객체를 취하기 때문에 객체를 값으로 전달하여 연산하나, 데이터프로퍼티처럼 사용
```

```jsx
 
    data() {
    return {
      counter: 0,
      name: '',
      confirmedname:''
    };
  },
  computed: {
    fullname (){
      console.log('Running again...');
      if (this.name === '') {
        return '';
      }
      return this.name+' '+'Kim';
    }
  },
  methods: {
       outputFullName (){
      console.log('Running again...');
      if (this.name === '') {
        return '';
      }
      return this.name+' '+'Kim';
    },
    }
```

```jsx
      <p>Your Name: {{ fullname }}</p>
```

이렇게 데이터 프로퍼티처럼 사용할 것이므로, 유일한 프로퍼티인 name 프로퍼티가 변경된 경우에만 재계산하고 재평가함

* watcher : 의존성 중 하나가 변경될 때 Vue에 실행하도록 지시할 수 있는 함수

```jsx
  data() {
    return {
      counter: 0,
      name: '',
      confirmedname:''
    };
  },
  watch: {
    name(){}
  },
```

name이 변경될 때마다 watch는 재실행. 데이터나 연산 프로퍼티의 이름을 감시자 메서드에서 메서드 이름으로 사용하면 프로퍼티에 있는 이름이 변경될 때마다 watch 메서드가 Vue에 의해 자동으로 실행

```jsx
  watch: {
    name(value){
      this.fullname = value + ' '+ 'Kim';
    }
  },
```

자동으로 watch 프로퍼티의 마지막 값을 인수로 가져옴. 두 번째 인수를 받는다면 오래된 값도 받아올 수 있음.

* Methods : 매번 재평가되어야 하는 이벤트나 데이터에 이용
* Computed: 다른 데이터에 의존하는 데이터에 이용
* Watch: 템플릿 내부에서 참조하지는 않지만 감시 가능, 데이터가 아닌 업데이트에 사용
* v-if (바로뒤) v-else / v-else-if : 조건에 해당하지 않으면 element가 아예 사라짐
* v-show : 조건에 맞지 않으면 display속성이 none이 되는  것, element는 그대로 존재함