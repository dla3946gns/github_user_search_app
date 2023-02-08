package com.example.user_github_list

import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.user_github_list.databinding.ActivityMainBinding
import com.example.user_github_list.ui.users.GithubUsersFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 1. 로딩바가 있는 리스트 페이징
 * 2. mvvm 패턴, Hilt 사용
 * 3. username으로 검색
 * 4. 유저 선택 시 해당 유저의 repository list 표시
 * 5. 리스트들은 페이징 시 결과가 없을 때까지 무한 스크롤
 * 6. 유저 및 레파지토리 상세 정보 추가 => 상세 페이지?
 * 7. 기타 사항은 임의로 판단하여 노출시키기
 * 8. Readme 작성
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}