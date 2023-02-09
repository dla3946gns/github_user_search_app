# github_user_search_app
스포카 github 유저 검색기 만들기 과제 Repository입니다.

1. 검색어를 작성하여 사용자 리스트를 불러옵니다.
![Screenshot_20230209_210428_user_github_list](https://user-images.githubusercontent.com/58911609/217815274-ffdc357c-e00c-41d6-8ce5-a2092faa08ab.jpg)
![Screenshot_20230209_210841_user_github_list](https://user-images.githubusercontent.com/58911609/217815285-2d273ec8-73b4-446a-82a5-6030b1c31c96.jpg)
<br/>
2. 사용자 프로필 이미지를 클릭하면 사용자 상세 페이지로 이동하고, 리스트 중 하나를 누르면 리포지토리 리스트 페이지로 이동합니다.
<br/>
3. 리포지토리 중 하나를 누르면 리포지토리 상세 페이지로 이동합니다.

라이브러리 스펙은 다음과 같습니다.
Hilt, Retrofit2, Gson, Glide, navigation component, paging3
<br/>
디자인 패턴은 MVVM이며 사용한 언어는 Kotlin입니다.
<br/>
Github에서 제공하는 검색 API를 참고하여 만들었습니다.
https://docs.github.com/ko/rest/search?apiVersion=2022-11-28
