- 기존에 사용되던 Appbar가 ActionMode가 실행되면 Contextual App bar로 변경된다.

 ![image](https://user-images.githubusercontent.com/69443895/174952225-c011b9bb-06de-4226-95e8-3b3abdc28007.png)
 
https://material.io/components/app-bars-top/android#contextual-action-bar

---

<br>

- 구현 과정에서 액션모드 시 나타나는 app bar 가 기존의 app bar를 밑으로 밀어내며 생성되는 문제가 발생했다

![toolbar1](https://user-images.githubusercontent.com/69443895/174952533-4e87a6b2-1886-4643-90fd-71853e0f000f.gif)

---
<br>

- https://hellose7.tistory.com/78
- 위 링크를 참고해 기존의 appbar 의 hide & show 동작을 통해 위의 문제를 해결할 수 있었다
- 하지만 액션모드에서 빠져나오는 경우 기존의 appbar가 다시 show 되는 부분이 어색해보였다

![toolbar3](https://user-images.githubusercontent.com/69443895/174953441-1dc4a119-c93c-4058-80cb-d96d0503d123.gif)

---
<br>

- appbar가 변경되는 부분을 더 자연스럽게 만들어보고 싶었다
- 다시 material design 문서를 살펴보니 실수했던 부분이 있었다

![image](https://user-images.githubusercontent.com/69443895/174953733-518e48b6-f877-4b1e-a0ea-28ce8940e106.png)

- 문서에 나와있는 windowActionModeOverlay를 true로 설정했어야 하는데 자동완성으로 인해 windowActionBarOverlay를 설정하고 있었다..
- 조금 더 꼼꼼하게 살펴보지 못한 나의 잘못이었다
- windowActionModeOverlay로 수정을 하니 내가 원하는 대로 동작을 했다

![toolbar2](https://user-images.githubusercontent.com/69443895/174954002-52148811-57fa-436e-8e6d-8c6dbf16a058.gif)

<br>


