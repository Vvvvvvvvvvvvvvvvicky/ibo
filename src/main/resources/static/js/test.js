var app = new Vue({
	el:'#app',
	data:{
		message:'hello'
	}
});

app.message='改变';

var app2 = new Vue({
	el: '#app-2',
	data: {
		message: '页面加载于 ' + new Date().toLocaleString()
	}
});
app2.message='测试一下';

var app3 = new Vue({
	el:'#app-3',
	data:{
		seen:true
	}
});
app3.seen=false;

var app4 = new Vue({
	el:'#app-4',
	data:{
		todos:[
			{text:'A啊'},
			{text:'B啊'},
			{text:'C啊'}
		]
	}
});
app4.todos.push({text:'新加一个D啊'});

var app5 = new Vue({
	el:'#app-5',
	data:{
		message:'我是可以翻转的'
	},
	methods:{
		reverse:function(){
			this.message = this.message.split('').reverse().join('')
		}
	}
});

var app6 = new Vue({
	el:"#app-6",
	data:{
		message:'测试'
	}
});


Vue.component('todo-item', {
	props: ['todo'],
	template: '<li>{{ todo.text }}</li>'
})

var app7 = new Vue({
  el: '#app-7',
  data: {
    groceryList: [
      { id: 0, text: '蔬菜' },
      { id: 1, text: '奶酪' },
      { id: 2, text: '随便其它什么人吃的东西' }
    ]
  }
})

var app8 = new Vue({
	  el: '#app-8',
	  data: {
	    message: '<span style="color:red">red</span>'
	  }
})

var app8 = new Vue({
	  el: '#app-9',
	  data: {
	    message: true
	  }
})
app8.message=false

var vm = new Vue({
  el: '#example',
  data: {
    message: 'Hello'
  },
  computed: {
    // 计算属性的 getter
    reversedMessage: function () {
      // `this` 指向 vm 实例
      return this.message.split('').reverse().join('')
    }
  }
})

var testCache = new Vue({
	el:'#testCache',
	computed: {
		now:function(){
	    	return Date.now()
		}
	}
});

var vm2 = new Vue({
	  el: '#demo',
	  data: {
	    firstName: 'Foo',
	    lastName: 'Bar',
	    fullName: 'Foo Bar'
	  },
	  watch: {
	    firstName: function (val) {
	      this.fullName = val + ' ' + this.lastName
	    },
	    lastName: function (val) {
	      this.fullName = this.firstName + ' ' + val
	    }
	  }
	})

vm2.firstName='lee'
vm2.lastName='lee'

	
var vm3 = new Vue({
  el: '#demo3',
  data: {
    firstName: 'Foo',
    lastName: 'Bar'
  },
  computed: {
    fullName: function () {
      return this.firstName + ' ' + this.lastName
    }
  }
})
vm3.firstName='lee'
vm3.lastName='lee'

var watchExampleVM = new Vue({
  el: '#watch-example',
  data: {
    question: '',
    answer: 'I cannot give you an answer until you ask a question!'
  },
  watch: {
    // 如果 `question` 发生改变，这个函数就会运行
    question: function (newQuestion, oldQuestion) {
      this.answer = 'Waiting for you to stop typing...'
      this.debouncedGetAnswer()
    }
  },
  created: function () {
    // `_.debounce` 是一个通过 Lodash 限制操作频率的函数。
    // 在这个例子中，我们希望限制访问 yesno.wtf/api 的频率
    // AJAX 请求直到用户输入完毕才会发出。想要了解更多关于
    // `_.debounce` 函数 (及其近亲 `_.throttle`) 的知识，
    // 请参考：https://lodash.com/docs#debounce
    this.debouncedGetAnswer = _.debounce(this.getAnswer, 500)
  },
  methods: {
    getAnswer: function () {
      if (this.question.indexOf('?') === -1) {
        this.answer = 'Questions usually contain a question mark. ;-)'
        return
      }
      this.answer = 'Thinking...'
      var vm = this
      axios.get('https://yesno.wtf/api')
        .then(function (response) {
          vm.answer = _.capitalize(response.data.answer)
        })
        .catch(function (error) {
          vm.answer = 'Error! Could not reach the API. ' + error
        })
    }
  }
})

var testClass = new Vue({
	el:'#testClass',
	data: {
	  isActive: true,
	  hasError: false
	}
});


var testClass2 = new Vue({
	el:'#testClass2',
	data: {
		  isActive: true,
		  error: null
		},
	computed: {
	  classObject: function () {
	    return {
	      active: this.isActive && !this.error,
	      'text-danger': this.error && this.error.type === 'fatal'
	    }
	  }
	}
});

//定义一个名为 button-counter 的新组件
Vue.component('button-counter', {
data: function () {
 return {
   count: 0
 }
},
template: '<button v-on:click="count++">You clicked me {{ count }} times.</button>'
})

new Vue({ el: '#components-demo' })

var example1 = new Vue({
  el: '#example-1',
  data: {
    items: [
      { message: 'Foo' },
      { message: 'Bar' }
    ]
  }
})

var test = new Vue({
	el:"#test",
	data:{
		message_a:''
	}
})
	
	