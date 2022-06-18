## RecyclerView & ItemTouchHelper

- 리사이클러뷰에서 일정 영역 스와이프 후 고정시키기
- 상단에서 스와이프 된는 영역과 상단 영역 스와이프 시 나타나는 하단 영역으로 구분

---

![itemtouchhelper2](https://user-images.githubusercontent.com/69443895/173527788-2bef6820-97b3-4921-978d-6e2e5f2d13c9.gif)


---

- 삭제 및 체크박스 기능 추가

![itemtouchhelper3](https://user-images.githubusercontent.com/69443895/173640393-90ae7ad5-ac70-46a7-8836-23bf463f3637.gif)

---
<br>

### 2022 06 18 수정

- 한 번에 한 아이템만 스와이프 상태 유지 가능하도록 수정
- 아이템 별 스와이프 여부를 tag 값으로 관리한 부분 수정 -> 리사이클러뷰 데이터 클래스에 스와이프가 되었는지를 따로 데이터로 가지고 있도록 수정

![itemtouchhelper4](https://user-images.githubusercontent.com/69443895/174437483-fdc1f880-c8b0-4be1-b371-7593fb3165b9.gif)
