- [Materials](#materials)
- [Basic](#basic)
  - [Create React App](#create-react-app)
  - [Create React Components with JSX](#create-react-components-with-jsx)
  - [Data flow with Props](#data-flow-with-props)
  - [Lists with .maps](#lists-with-maps)
  - [Validating Props with Prop Types](#validating-props-with-prop-types)
  - [Component Lifecycle](#component-lifecycle)
  - [Thinking in React Component State](#thinking-in-react-component-state)
  - [Practicing this setState](#practicing-this-setstate)
  - [Loading states](#loading-states)
  - [Smart vs Dumb](#smart-vs-dumb)
  - [AJAX on React](#ajax-on-react)
  - [Promises](#promises)
  - [Async Await](#async-await)
  - [Updating Movie](#updating-movie)
  - [CSS for Movie](#css-for-movie)
  - [Building for Production](#building-for-production)
  - [Redux](#redux)
  - [To Do List](#to-do-list)
  - [React Redux](#react-redux)
- [Redux Toolkit](#redux-toolkit)
- [Redux SAGA](#redux-saga)
- [Redux Debugger in Chrome](#redux-debugger-in-chrome)

----

# Materials

* [create react app](https://github.com/facebook/create-react-app)
  * react app wizard
* [nomad academy](https://academy.nomadcoders.co/courses/category/KR)
  * react class
* [reactjs @ inflearn](https://www.inflearn.com/course/reactjs-web/)
  * react class for beginner
  * [src](https://github.com/nomadcoders/movie_app)
* [ReactJS로 웹 서비스 만들기](https://academy.nomadcoders.co/p/reactjs-fundamentals)
  * [src 2019 update](https://github.com/nomadcoders/movie_app_2019)
* [초보자를 위한 리덕스 101](https://academy.nomadcoders.co/courses/235420/lectures/13817530)
  * [src](https://github.com/nomadcoders/vanilla-redux)
* [Create-React-App: A Closer Look](https://github.com/nitishdayal/cra_closer_look)
  * stuffs in create-react-app in detail

# Basic

## Create React App

[Webpack](https://webpack.js.org/) 은 ES6 를 browser 가 이해할 수 있는 code 로 transpile 한다.

create-react-app 으로 startup-repo 를 생성할 수 있다. create-react-app 은 Webpack 을 포함한다.

```bash
$ brew install node.js
$ npm install -g create-react-app
$ create-react-app my-app
$ cd my-app
$ npm start
```

## Create React Components with JSX

`public/index.html` 에 `id="root"` 인 `div` 가 있다.

```html
<div id="root"></div>
```

`src/index.js` 는 `App` component 를 `id="root"` 인 `div` 에 rendering 하고 있다.

```js
ReactDOM.render(<App />, document.getElementById('root'));
```

`src/App.js` 는 `App` component 가 정의되어 있다.

```js
import React, {Component} from 'react';
import './App.css';
import Movie from './Movie';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Movie />
        <Movie />
      </div>
    );
  }
}

export default App;
```

`src/App.js` 는 `Movie` component 를 rednering 하고 있다. `Movie` component 는 다시 `MoviePoser` component 를 rendering 하고 있다.

`src/Movie.js` 는 `Movie, MoviePoster` component 가 정의되어 있다.

```js
import React, {Component} from 'react';
import './Movie.css';

class Movie extends Component {
  render() {
    return (
      <div>
        <MoviePoster />
        <h1>Hello This is a movie</h1>
      </div>
    )
  }
}

class MoviePoster extends Component {
  render() {
    return (
      <img src='http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778581_STD.jpg'/>
    )
  }
}

export default Movie;
```

## Data flow with Props

data 를 `src/App.js` 에 선언해 보자. 그리고 `Movie` component 에게 props 형태로 전달한다.

```js
...
const movieTitles = [
  "Matrix",
  "Full Metal Jacket",
  "Oldboy",
  "Star wars"
]

const movieImages = [
  'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778581_STD.jpg',
  'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778470_STD.jpg',
  'https://cdn1.thr.com/sites/default/files/imagecache/768x433/2017/06/143289-1496932680-mm_2012_047_italy_57_-_h_2017.jpg',
  'https://cdn1.thr.com/sites/default/files/2017/06/143226-1496932903-mm_2012_047_italy_11_-_embed_2018.jpg',
]

class App extends Component {
  render() {
    return (
      <div className="App">
        <Movie title={movieTitles[0]} poster={movieImages[0]}/>
        <Movie title={movieTitles[1]} poster={movieImages[1]}/>
        <Movie title={movieTitles[2]} poster={movieImages[2]}/>
        <Movie title={movieTitles[3]} poster={movieImages[3]}/>
      </div>
    );
  }
}
...
```

`Movie` component 는 props 의 일부를 다시 `MoviePoster` component 에게 전달한다.

```js
...
class Movie extends Component {
  render() {
    console.log(this.props);
    return (
      <div>
        <MoviePoster poster={this.props.poster}/>
        <h1>{this.props.title}</h1>
      </div>
    )
  }
}

class MoviePoster extends Component {
  render() {
    return (
      <img src={this.props.poster}/>
    )
  }
}
...
```

JSX 를 이용하여 각 component 의 props 를 rendering 한다.

## Lists with .maps

`movies` 배열을 만들고 `App` component 를 `.map` function 을 활용하여 구현해보자.

```js
const movies = [
  {
    title: "Matrix",
    poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778581_STD.jpg',
  },
  {
    title: "Full Metal Jacket",
    poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778470_STD.jpg',
  },
  {
    title: "Oldboy",
    poster: 'https://cdn1.thr.com/sites/default/files/imagecache/768x433/2017/06/143289-1496932680-mm_2012_047_italy_57_-_h_2017.jpg',
  },
  {
    title: "Star wars",
    poster: 'https://cdn1.thr.com/sites/default/files/2017/06/143226-1496932903-mm_2012_047_italy_11_-_embed_2018.jpg',
  },
]

class App extends Component {
  render() {
    return (
      <div className="App">
        {movies.map(movie => {
          return <Movie title={movie.title} poster={movie.poster} />
        })}
      </div>
    );
  }
}
```

## Validating Props with Prop Types

`static propTypes` 를 선언하여 props 의 값을 제어할 수 있다. 이때 PropTypes module 이 설치되어야 한다. `yarn add PropTypes`

```js
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import './Movie.css';

class Movie extends Component {
  static propTypes = {
    title: PropTypes.string.isRequired,
    poster: PropTypes.string.isRequired
  }
  render() {
    console.log(this.props);
    return (
      <div>
        <MoviePoster poster={this.props.poster}/>
        <h1>{this.props.title}</h1>
      </div>
    )
  }
}

class MoviePoster extends Component {
  static propTypes = {
    poster: PropTypes.string.isRequired
  }
  render() {
    return (
      <img src={this.props.poster}/>
    )
  }
}

export default Movie;
```

## Component Lifecycle

하나의 component 는 다음과 같은 순서로 `Render, Update` 가 수행된다. Override function 의 순서를 주의하자.

```js
class App extends Component {
  // Render: componentWillMount() -> render() -> componentDidMount()
  //
  // Update: componentWillReceiveProps() -> shouldComponentUpdate() -> 
  // componentWillUpate() -> render() -> componentDidUpdate()
  componentWillMount() {
    console.log("componentWillMount");
  }
  componentDidMount() {
    console.log("componentDidMount");
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {movies.map((movie, index) => {
          return <Movie title={movie.title} poster={movie.poster} key={index} />
        })}
      </div>
    );
  }
}
```

## Thinking in React Component State

`App` component 에 state 를 선언하고 `render()` 에서 바꿔보자. `this.setState()` 함수를 호출하면 `render()` 가 호출된다. `state` 를 바꾸고 `this.setState()` 를 호출하여 화면을 업데이트한다. 

```js
class App extends Component {
  state = {
    greeting: 'Hello'
  }
  componentDidMount() {
    setTimeout(() => {
      //this.state.greeting = 'something'
      this.setState({
        greeting: 'Hello Again'
      })
    }, 2000)
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.greeting}
        {movies.map((movie, index) => {
          return <Movie title={movie.title} poster={movie.poster} key={index} />
        })}
      </div>
    );
  }
}
```

## Practicing this setState

`App` component 의 `state` 으로 title, poster 를 옮기자. 그리고 일정 시간 이후에 `state` 을 변경해 보자. `...this.state.movies` 를 이용하면 기존의 array 에 새로운 원소들을 추가할 수 있다.

```js
class App extends Component {

  state = {
    greeting: 'Hello World',
    movies: [
      {
        title: "Matrix",
        poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778581_STD.jpg',
      },
      {
        title: "Full Metal Jacket",
        poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778470_STD.jpg',
      },
      {
        title: "Oldboy",
        poster: 'https://cdn1.thr.com/sites/default/files/imagecache/768x433/2017/06/143289-1496932680-mm_2012_047_italy_57_-_h_2017.jpg',
      },
      {
        title: "Star wars",
        poster: 'https://cdn1.thr.com/sites/default/files/2017/06/143226-1496932903-mm_2012_047_italy_11_-_embed_2018.jpg',
      },
    ]    
  }  
  componentDidMount() {
    setTimeout(() => {
      //this.state.greeting = 'something'
      this.setState({
        movies: [
        ...this.state.movies,
        {
          tilte: "Trainspotting",
          poster: 'https://cdn1.thr.com/sites/default/files/2017/06/143226-1496932903-mm_2012_047_italy_11_-_embed_2018.jpg',
        }
      ]
      })
    }, 2000)
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.greeting}
        {this.state.movies.map((movie, index) => {
          return <Movie title={movie.title} poster={movie.poster} key={index} />
        })}
      </div>
    );
  }
}
```

이 방법을 이용하면 스크롤을 아래로 내렸을 때 infinite scroll 을 구현할 수 있다.

## Loading states

loading screen 을 구현해 보자. `App` component 에 rendering 을 시작하자 마자 `Loading...` 을 출력하고 일정 시간이 지나면 state 을 업데이트하여 movies 가 rendering 되도록 해보자.

```js
class App extends Component {
  state = {
    greeting: 'Hello World',
  }  
  componentDidMount() {
    setTimeout(() => {
      console.log("change state...")
      //this.state.greeting = 'something'
      this.setState({
        movies: [
          {
            title: "Matrix",
            poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778581_STD.jpg',
          },
          {
            title: "Full Metal Jacket",
            poster: 'http://ojsfile.ohmynews.com/STD_IMG_FILE/2014/1202/IE001778470_STD.jpg',
          },
          {
            title: "Oldboy",
            poster: 'https://cdn1.thr.com/sites/default/files/imagecache/768x433/2017/06/143289-1496932680-mm_2012_047_italy_57_-_h_2017.jpg',
          },
          {
            title: "Star wars",
            poster: 'https://cdn1.thr.com/sites/default/files/2017/06/143226-1496932903-mm_2012_047_italy_11_-_embed_2018.jpg',
          },
        ]    
      })
    }, 2000)
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title} poster={movie.poster} key={index} />
    })
    return movies;
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : 'Loading...'}
      </div>
    );
  }
}
```

## Smart vs Dumb

Smart component 는 state 이 있는 component 이다. Dumb component 는 state 이 없는 component 이다. props 만 있다.
Dumb component 를 stateless 혹은 functional component 라고도 한다. Dumb component 는 state 가 필요없을 때 간결한 코드를 만들기 위해 사용한다.
그러나 state 가 없기도 하고 componentDidMount, componentWillMount 와 같은 함수들을 사용할 수 없다.

다음은 `Movie, MoviePoster` component 를 Dumb component 로 수정한 것이다.

```js
function Movie({title, poster}) {
  return (
    <div>
      <MoviePoster poster={poster}/>
      <h1>{title}</h1>
    </div>
  );
}
Movie.propTypes = {
  title: PropTypes.string.isRequired,
  poster: PropTypes.string.isRequired
}

// class MoviePoster extends Component {
//   static propTypes = {
//     poster: PropTypes.string.isRequired
//   }
//   render() {
//     return (
//       <img src={this.props.poster} alt="Movie Poster"/>
//     )
//   }
// }
function MoviePoster({poster}) {
  return (
    <img src={poster} alt="Movie Poster"/>
  );
}
MoviePoster.propTypes = {
  poster: PropTypes.string.isRequired
}
```

## AJAX on React

AJAX 는 Asynchrous JavaScript and XML 이다. 그러나 XML 은 사용하지 않고 JSON 을 사용한다. AJAJ 로 바뀌어야 한다???
다음은 fetch 함수를 이용하여 XHR (XML HTTP Request) 를 실행한 것이다.

```js
class App extends Component {
  state = {};
  componentDidMount() {
    fetch('https://yts.ag/api/v2/list_movies.json?sort_by=rating')
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title} poster={movie.poster} key={index} />
    })
    return movies;
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : 'Loading...'}
      </div>
    );
  }
}
```

## Promises

[javascript proxy](/js/README.md#promise) 를 참고하여 이해하자. 다음은 앞서 작성한 XHR 의 handler 를 추가한 것이다.

```js
class App extends Component {
  state = {};
  componentDidMount() {
    fetch('https://yts.ag/api/v2/list_movies.json?sort_by=rating')
    .then(potato => potato.json())
    .then(json => console.log(json))
    .catch(err => console.log(err));
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title} poster={movie.poster} key={index} />
    })
    return movies;
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : 'Loading...'}
      </div>
    );
  }
}
```

그러나 CORS 설정이 되어 있지 않아서 error 가 발생한다. 다음과 같이 proxy 를 설정하면 해결할 수 있다.

```js

class App extends Component {
  state = {};
  componentDidMount() {
    const proxyurl = "https://cors-anywhere.herokuapp.com/";
    const url = 'https://yts.ag/api/v2/list_movies.json?sort_by=rating'; // site that doesn’t send Access-Control-*
    fetch(proxyurl + url)
    .then((resp) => resp.json())
    .then(json => console.log(json))
    .catch(err => console.log(err));
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title} poster={movie.poster} key={index} />
    })
    return movies;
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : 'Loading...'}
      </div>
    );
  }
}
```

그러나 promise 는 then() 을 무수히 만들어 낸다. 이것을 Callback Hell 이라고 한다. Async Await 을 이용하면 Callback Hell 을 탈출할 수 있다.

## Async Await

[JavaScript async](/js/README.md#async) 를 참고하여 이해하자. promise 는 then() 의 남용으로 Call Back 함수가 많아져서 code 의 readability 를 떨어뜨린다. async, await 을 이용하면 call back functions 을 줄일 수 있고 code 의 readability 를 끌어 올릴 수 있다.

`async` 로 function 을 선언하면 function 안에서 `await` 로 기다릴 수 있다. `await` 로 기다리는 것은 `promise` 이다.

```js
class App extends Component {
  state = {};
  componentDidMount() {
    this._getMovies();
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title} poster={movie.large_cover_image} key={index} />
    })
    return movies;
  }
  _getMovies = async () => {
    const movies = await this._callApi();
    this.setState({
      movies
    });
  }
  _callApi = () => {
    const proxyurl = "https://cors-anywhere.herokuapp.com/";
    const url = 'https://yts.ag/api/v2/list_movies.json?sort_by=rating'; // site that doesn’t send Access-Control-*
    return fetch(proxyurl + url)
    .then((resp) => resp.json())
    .then(json => json.data.movies)
    .catch(err => console.log(err));
  }
  render() {
    console.log("render");
    return (
      <div className="App">
        {this.state.movies ? this._renderMovies() : 'Loading...'}
      </div>
    );
  }
}
```

## Updating Movie

이제 XHR 을 통해 얻어온 json 데이터를 화면에서 업데이트해 보자.

```js
class App extends Component {
  state = {};
  componentDidMount() {
    this._getMovies();
  }
  _renderMovies = () => {
    const movies = this.state.movies.map((movie, index) => {
      return <Movie title={movie.title_english} 
      poster={movie.medium_cover_image}       
      key={index} 
      genres={movie.generes}
      synopsis={movie.synopsis}
      />
    })
    return movies;
  }
...
```

```js
function Movie({title, poster}) {
  return (
    <div>
      <MoviePoster poster={poster}/>
      <h1>{title}</h1>
    </div>
  );
}
Movie.propTypes = {
  title: PropTypes.string.isRequired,
  poster: PropTypes.string.isRequired,
  key: PropTypes.string.isRequired,
  genres: PropTypes.string.isRequired,
  synopsis: PropTypes.string.isRequired,
}
```

이제 `Movie` component 에 XHR 을 통하여 얻은 데이터 `title, poster, key, genres, synopsis` 를 표시해 보자.

```js
import React, {Component} from 'react';
import PropTypes from 'prop-types';
import './Movie.css';

function Movie({title, poster, genres, synopsis}) {
  return (
    <div clasName="Movie">
      <div className="Movie__Columns">
      <MoviePoster poster={poster} alt={title}/>
      </div>
      <div className="Movie__Columns">
        <h1>{title}</h1>
        <div className="Movie__Genre">
          {genres.map((genre, index) => <MovieGenre genre={genre} key={index} />)}
        </div>
        <p className="Movie__Synopsis">
          {synopsis}
        </p>
      </div>
    </div>
  );
}
function MoviePoster({poster, alt}) {
  return (
    <img src={poster} alt={alt} title={alt} className="Movie__Poster" />
  );
}
function MovieGenre({genre}) {
  return (
    <span className="Movie__Genre">{genre}</span>
  );
}
Movie.propTypes = {
  title: PropTypes.string.isRequired,
  poster: PropTypes.string.isRequired,
  key: PropTypes.string.isRequired,
  genres: PropTypes.string.isRequired,
  synopsis: PropTypes.string.isRequired,
}
MoviePoster.propTypes = {
  poster: PropTypes.string.isRequired,
  alt: PropTypes.string.isRequired,
}
MovieGenre.proptype = {
  genre: PropTypes.string.isRequired,
}

export default Movie;
```

## CSS for Movie

[react.js fundamentals src 2019 update](https://github.com/nomadcoders/movie_app_2019)

[kakao-clone-v2 @ github](git@github.com:nomadcoders/kakao-clone-v2.git) 를 통해 css 를 더욱 배울 수 있다.

## Building for Production

지금까지 제작한 react.js app 을 `github` 에 publishing 해보자.

`npm run build` 혹은 `yarn build` 를 수행하면 `build` 디렉토리가 만들어진다. `build` 에는 압축된 static files 들이 만들어 진다.

`gh-pages` module 을 추가해 보자.

```bash
$ yarn add --dev gh-pages
```

`package.json` 의 "`homepage, predploy, deploy`" 을 다음과 같이 수정한다.

```js
  ...
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject",
    "predeploy": "npm run build",
    "deploy": "gh-pages -d build -r git@github.com:iamslash/examplesofweb.git"
  },
  ...
  "homepage": "https://iamslash.github.io/examplesofweb",
  "devDependencies": {
    "gh-pages": "^2.2.0"
  }
  ...
```

이제 배포해 본다.

```bash
$ yarn run deploy
```

## Redux

* [초보자를 위한 리덕스 101](https://academy.nomadcoders.co/courses/235420/lectures/13817530)
  * [src](https://github.com/nomadcoders/vanilla-redux)

Redux 는 state 를 관리하기 위한 거대한 event loop 이다. Action 은 event 를 말하고 Reducer 는 event handler 이다. 즉, Reducer 는 함수이고 변경된 state 를 return 한다. Reducer 의 첫번째 argument 는 state 이고 두번째 argument 는 action 이다.

Store 는 Application 의 state 이다. Store 를 생성하기 위해서는 Reducer 가 필요하다. Store instance 의 getState() 를 호출하면 현재 state 를 얻어올 수 있다. Store instance 의 dispatch() 를 특정 action 과 함께 호출하면 Store instance 에 등록된 Reducer 가 그 action 을 두번째 argument 로 호출된다. 

또한 Store instance 의 subscribe() 를 함수와 함께 호출하면 Store 가 변경될 때 마다 그 함수가 호출된다. 그 함수에서 Store instance 의 getState() 를 호출하면 변경된 state 를 얻어올 수 있다.

[리덕스(Redux)란 무엇인가?](https://voidsatisfaction.github.io/2017/02/24/what-is-redux/)

Redux 는 다음과 같은 흐름으로 처리된다.

* Store 에 Component 를 subscribe 한다.
* User 가 button 을 click 하면 Store 의 dispatch 함수가 특정 action 을 argument 로 호출된다.
* Store 에 등록된 Reducer 가 호출된다. 
* Reducer 는 state 를 변경하여 return 한다.
* Store 에 미리 subscribe 된 Component 에게 변경된 state 가 전달된다.
* Component 는 변경된 state 를 rendering 한다.

```js
import { createStore } from "redux";

const add = document.getElementById("add");
const minus = document.getElementById("minus");
const number = document.querySelector("span");

number.innerText = 0;

const ADD = "ADD";
const MINUS = "MINUS";

const countModifier = (count = 0, action) => {
  switch (action.type) {
    case ADD:
      return count + 1;
    case MINUS:
      return count - 1;
    default:
      return count;
  }
};

const countStore = createStore(countModifier);

const onChange = () => {
  number.innerText = countStore.getState();
};

countStore.subscribe(onChange);

const handleAdd = () => {
  countStore.dispatch({ type: ADD });
};

const handleMinus = () => {
  countStore.dispatch({ type: MINUS });
};

add.addEventListener("click", handleAdd);
minus.addEventListener("click", handleMinus);
```

## To Do List

Redux 를 이용하여 간단한 To Do list 를 구현해 본다.

* [Vanilla-redux To Do List by nomad coders](https://github.com/nomadcoders/vanilla-redux/blob/794f2a3eb7d169de7ca240b163e481a22653f7bd/src/index.js)

```js
import { createStore } from "redux";
const form = document.querySelector("form");
const input = document.querySelector("input");
const ul = document.querySelector("ul");

const ADD_TODO = "ADD_TODO";
const DELETE_TODO = "DELETE_TODO";

const addToDo = text => {
  return {
    type: ADD_TODO,
    text
  };
};

const deleteToDo = id => {
  return {
    type: DELETE_TODO,
    id
  };
};

const reducer = (state = [], action) => {
  switch (action.type) {
    case ADD_TODO:
      const newToDoObj = { text: action.text, id: Date.now() };
      return [newToDoObj, ...state];
    case DELETE_TODO:
      const cleaned = state.filter(toDo => toDo.id !== action.id);
      return cleaned;
    default:
      return state;
  }
};

const store = createStore(reducer);

store.subscribe(() => console.log(store.getState()));

const dispatchAddToDo = text => {
  store.dispatch(addToDo(text));
};

const dispatchDeleteToDo = e => {
  const id = parseInt(e.target.parentNode.id);
  store.dispatch(deleteToDo(id));
};

const paintToDos = () => {
  const toDos = store.getState();
  ul.innerHTML = "";
  toDos.forEach(toDo => {
    const li = document.createElement("li");
    const btn = document.createElement("button");
    btn.innerText = "DEL";
    btn.addEventListener("click", dispatchDeleteToDo);
    li.id = toDo.id;
    li.innerText = toDo.text;
    li.appendChild(btn);
    ul.appendChild(li);
  });
};

store.subscribe(paintToDos);

const onSubmit = e => {
  e.preventDefault();
  const toDo = input.value;
  input.value = "";
  dispatchAddToDo(toDo);
};

form.addEventListener("submit", onSubmit);
```

## React Redux

앞서 제작한 To Do List App 을 React Redux 를 사용하여 더욱 효율적으로 구현해보자.

* [Vanilla-redux react-redux by nomad coders](https://github.com/nomadcoders/vanilla-redux/tree/ccaa1acd081f27239f2cc8ad3c571bd0a9923f73/src)

React-Router 는 url path 에 따라 routing 기능을 지원하는 library 이다. url `/` 은 `Home` component, `/:id` 는 `Detail` component 로 routing 된다.

```js
import React from "react";
import { HashRouter as Router, Route } from "react-router-dom";
import Home from "../routes/Home";
import Detail from "../routes/Detail";

function App() {
  return (
    <Router>
      <Route path="/" exact component={Home}></Route>
      <Route path="/:id" component={Detail}></Route>
    </Router>
  );
}

export default App;
```

react-redux 의 Provider 는 state 변경사항을 자손에게 전달할 수 있게 해준다.

```js
import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";
import { Provider } from "react-redux";
import store from "./store";

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById("root")
);
```

`connect` 를 `mapStateToProps` 와 함께 호출하면 특정 Component 를 연결할 수 있다. 즉, state 가 변경되면 `mapStateToProps` 가 호출된다. `mapStateToProps` 는 state 가 포함된 props 를 리턴한다. 리턴된 props 는 component 에게 전달된다. 

```js
import React, { useState } from "react";
import { connect } from "react-redux";

function Home({ toDos }) {
  const [text, setText] = useState("");
  function onChange(e) {
    setText(e.target.value);
  }
  function onSubmit(e) {
    e.preventDefault();
    setText("");
  }
  return (
    <>
      <h1>To Do</h1>
      <form onSubmit={onSubmit}>
        <input type="text" value={text} onChange={onChange} />
        <button>Add</button>
      </form>
      <ul>{JSON.stringify(toDos)}</ul>
    </>
  );
}

function mapStateToProps(state) {
  return { toDos: state };
}

export default connect(mapStateToProps)(Home);
```

`connect` 의 두번째 argument 는 dispatch function 을 넘겨줄 수 있다. `mapDispatchToProps` 는 dispatch function 을 props 에 담아서 리턴한다. 리턴된 props 는 `connect` 에 연결된 component 에 전달된다. component 는 특정 시점에 dispatch function 을 호출하고 특정한 action 이 발생된다. 그리고 reducer 가 호출된다.

```js
import React, { useState } from "react";
import { connect } from "react-redux";
import { actionCreators } from "../store";

function Home({ toDos, addToDo }) {
  const [text, setText] = useState("");
  function onChange(e) {
    setText(e.target.value);
  }
  function onSubmit(e) {
    e.preventDefault();
    addToDo(text);
    setText("");
  }
  return (
    <>
      <h1>To Do</h1>
      <form onSubmit={onSubmit}>
        <input type="text" value={text} onChange={onChange} />
        <button>Add</button>
      </form>
      <ul>{JSON.stringify(toDos)}</ul>
    </>
  );
}

function mapStateToProps(state) {
  return { toDos: state };
}

function mapDispatchToProps(dispatch) {
  return {
    addToDo: text => dispatch(actionCreators.addToDo(text))
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(Home);
```

다음은 `connect` 에 두번째 argument 로 `mapDispatchToProps` 를 전달하고 `ToDo` component 와 연결한다. `ToDo` component 의 button 이 click 되면 `ToDo` component 에 전달된 props 의 두번째 요소인 dispatch function 이 호출된다. dispatch function 에 해당하는 `onBtnClick` 이 호출되면 `DELETE` action 이 발생하고 Reducer 가 호출된다.

```js
import React from "react";
import { connect } from "react-redux";
import { actionCreators } from "../store";

function ToDo({ text, onBtnClick }) {
  return (
    <li>
      {text} <button onClick={onBtnClick}>DEL</button>
    </li>
  );
}

function mapDispatchToProps(dispatch, ownProps) {
  return {
    onBtnClick: () => dispatch(actionCreators.deleteToDo(ownProps.id))
  };
}

export default connect(null, mapDispatchToProps)(ToDo);
```

`Reducer` 에서 `DELETE` 을 처리한다. `filter` 를 이용하여 특정 id 를 제거한 목록을 변경된 state 로 리턴한다.

```js
import { createStore } from "redux";

const ADD = "ADD";
const DELETE = "DELETE";

const addToDo = text => {
  return {
    type: ADD,
    text
  };
};

const deleteToDo = id => {
  return {
    type: DELETE,
    id: parseInt(id)
  };
};

const reducer = (state = [], action) => {
  switch (action.type) {
    case ADD:
      return [{ text: action.text, id: Date.now() }, ...state];
    case DELETE:
      return state.filter(toDo => toDo.id !== action.id);
    default:
      return state;
  }
};

const store = createStore(reducer);

export const actionCreators = {
  addToDo,
  deleteToDo
};

export default store;
```

# Redux Toolkit

createAction 은 Action 생성을 쉽게 해준다.

createReducer 는 Reducer 생성을 쉽게 해준다.

configureStore 는 ???

createSlice 는 action, reducer 생성을 쉽게 해준다.

# Redux SAGA

* [redux-saga](https://github.com/redux-saga/redux-saga)
  * [한글](https://mskims.github.io/redux-saga-in-korean/)

# Redux Debugger in Chrome

* [ #4.3 configureStore](https://academy.nomadcoders.co/courses/235420/lectures/14735315)

