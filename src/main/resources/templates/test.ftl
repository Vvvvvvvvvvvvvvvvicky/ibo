<html>
	<head>
		<link rel="stylesheet" href="/css/test.css"></link>
		<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
		<!-- 因为 AJAX 库和通用工具的生态已经相当丰富，Vue 核心代码没有重复 -->
		<!-- 提供这些功能以保持精简。这也可以让你自由选择自己更熟悉的工具。 -->
		<script src="https://cdn.jsdelivr.net/npm/axios@0.12.0/dist/axios.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/lodash@4.13.1/lodash.min.js"></script>
		<title>测试</title>
	</head>
	<body>
		<h1>${message},${name}</h1>
	</body>
		<div id="app" v-once>
			{{message}}
		</div>
		<div id="app-2">
		  	<span v-bind:title="message">
		           	鼠标悬停几秒钟查看此处动态绑定的提示信息！
		  	</span>
		</div>
		<div id="app-3">
			<p v-if="seen">你看见我了</p>
		</div>
		<div id="app-4">
			<ol>
				<li v-for="todo in todos">
					{{todo.text}}
				</li>
			</ol>
		</div>
		<div id="app-5">
			<p>{{message}}</p>
			<button v-on:click="reverse">翻转字符串</button>
		</div>
		<div id="app-6">
			<p>{{message}}</p>
			<input v-model="message">
		</div>
		<div id="app-7">
		  <ol>
		    <todo-item
		      v-for="item in groceryList"
		      v-bind:todo="item"
		      v-bind:key="item.id">
		    </todo-item>
		  </ol>
		</div>
		<div id="app-8" v-html>
			<span v-html="message"></span>
		</div>
		<div id="app-9">
			{{message ? 1:2}}
		</div>
		<div id="testCache">
		  <p>Now: "{{ now }}"</p>
		</div>
		<div id="example">
		  <p>Now: "{{ reversedMessage }}"</p>
		</div>
		<div id="demo">{{ fullName }}</div>
		<div id="demo3">{{ fullName }}</div>
		<div id="watch-example">
		  <p>
		    Ask a yes/no question:
		    <input v-model="question">
		  </p>
		  <p>{{ answer }}</p>
		</div>
		<div id="testClass" class="static"
		     v-bind:class="{ active: isActive, 'text-danger': hasError }">
		</div>
		<div id ="testClass2" v-bind:class="classObject"></div>
		<div id="components-demo">
		  <button-counter></button-counter>
		</div>
		
		<template v-if="ok">
		  <h1>Title</h1>
		  <p>Paragraph 1</p>
		  <p>Paragraph 2</p>
		</template>
		
		<h1 v-if="ok">Yes</h1>
		<h1 v-else>No</h1>
				
		<div v-if="type === 'A'">
		  A
		</div>
		<div v-else-if="type === 'B'">
		  B
		</div>
		<div v-else-if="type === 'C'">
		  C
		</div>
		<div v-else>
		  Not A/B/C
		</div>
		
		<template v-if="loginType === 'username'">
		  <label>Username</label>
		  <input placeholder="Enter your username">
		</template>
		<template v-else>
		  <label>Email</label>
		  <input placeholder="Enter your email address">
		</template>
		
		<h1 v-show="ok">Hello!</h1>
		<!--如果需要非常频繁地切换，则使用 v-show 较好；如果在运行时条件很少改变，则使用 v-if 较好。-->
		
		<ul id="example-1">
		  <li v-for="item in items">
		    {{ item.message }}
		  </li>
		</ul>
		
		<div id='test'>
			<input v-model="message_a">
			<p>当前值为：{{message_a}}</p>
		</div>
		
		
		
		
		<script type="text/javascript" src="/js/test.js"></script>
		
		
</html>