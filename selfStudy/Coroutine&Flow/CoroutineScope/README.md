## 스코프 별 동작 방식 살펴보기
---

- CoroutineScope 는 안드로이드 구성요소(Activity, Fragment, ViewModel)의 Lifecycle에 알맞게 할당이 해제되어야 한다
- 그렇지 않으면 해제가 되어야 하는 Job이 계속 동작하여 메모리 누수로 이어질 수 있다
---
<br>

### GlobalScope
![globalscope](https://user-images.githubusercontent.com/69443895/168083155-a69a54f6-8e2e-413c-9ecf-2b0b06a3da7a.gif)

- Acitivity 에서 뒤로가기 후 onDestory가 호출된 이후에도 Job이 계속 동작된다


---
<br>

### lifecycle.coroutineScope & lifecycleScope
![lifecycle_coroutineScope](https://user-images.githubusercontent.com/69443895/168083980-4f59412c-e33c-4540-b995-b0bc6a6676c3.gif)

![lifecycleScope](https://user-images.githubusercontent.com/69443895/168086559-fc2d9c3b-47c4-4a85-a3a0-bbc96b1ea22f.gif)

- Activity에서 사용
- Activity onDestroy 호출 시 lifecycleScope 또한 해제되어 내부 Job들이 취소된다 -> collect 중지

![image](https://user-images.githubusercontent.com/69443895/168086492-4c51f914-61ab-4ae1-a4bc-1f6099f07b7d.png)

https://developer.android.com/topic/libraries/architecture/coroutines?hl=ko

- 둘 모두 액티비티에서 뒤로가기 시 job들이 취소되었다가 다시 해당 액티비티로 돌아가면 처음부터 데이터를 수집한다

---
<br>

### lifecycleScope 에서 onDestroy 말고 onStop 까지만 호출되면??

- 홈 키를 누르는 경우
- 홈 화면이 보이고 액티비티가 보이지 않는 상황에서도 job이 계속 동작한다

![lifecycleScope_onStop](https://user-images.githubusercontent.com/69443895/168087135-2e902206-e209-4605-af0f-1f4be69a14f4.gif)

---
<br>

### lifecycleScope.launch + repeatOnLifecycle(Lifecycle.State.STARTED)
![repeatOnLifecycle](https://user-images.githubusercontent.com/69443895/168088951-1acd4bc4-efe9-48f2-91f8-5b8d3934ed47.gif)

- onStop에서 collect를 중지하게 만들수는 있지만 빠뜨릴 가능성 등의 문제 발생
- repeatOnLifecycle 는 액티비티가 포그라운드에 있을 때로 한정지어, 특정 라이프 사이클이 Trigger 되었을 때 동작하도록 만들 수 있다
- 홈 버튼을 눌러 백그라운드로 전환된 경우 job 중단된다
- 다시 포그라운드 전환 시 처음부터 데이터 수집

![image](https://user-images.githubusercontent.com/69443895/168090237-bba8b507-17ad-447e-8d78-6e2ebcebc421.png)

- 최소 onStart가 되었을 때 블록 내부의 메서드 수행
- onStop 시 코루틴 job cancel
- 안전하게 flow를 collect 할 수 있는 방법이다!!!

---
<br>

### lifecycleScope.launchWhenStarted

![launchWhenStarted](https://user-images.githubusercontent.com/69443895/168090759-2716f2ea-05c8-4253-ad4f-6269fa3c3474.gif)

- collect는 잠시 중단되지만, emit은 계속 진행되어 리소스 낭비 발생
- 코루틴을 취소하지는 않는것

https://stackoverflow.com/questions/71113597/difference-between-launchwhenstarted-and-repeatonlifecyclestarted-in-collectin

---
