package com.example.asyncstudy

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.example.asyncstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var activityMainBinding : ActivityMainBinding? = null
    var task : BackgroundAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding = binding

        setContentView(activityMainBinding!!.root)



        activityMainBinding?.btnStart?.setOnClickListener {
            task = BackgroundAsyncTask(activityMainBinding?.progressBar!!, activityMainBinding?.tvTitle!!)
            // AsyncTask는 한 번 만들어지고 취소되면 다시 시작 불가 -> 따라서 위와 같이 버튼 클릭 시 매번 만들어지도록 구현 = 단점..

            task?.execute()
        }
        activityMainBinding?.btnStop?.setOnClickListener {
            task?.cancel(true)  //실행한 것을 취소할 것인지 -> true 넣어준다
        }
    }

    override fun onPause() {

        task?.cancel(true)  //AsyncTask는 해당 액티비티 전환 시 자동 종료x -> 명시적으로 해줘야 한다
                                                // 상황에 따라 액티비티 전환되어도 작업이 진행되어야 한다면 cancel 안 할 수도 있다!

        super.onPause()

    }
}


class BackgroundAsyncTask(
        val progressBar : ProgressBar,
        val progressText : TextView
) : AsyncTask<Int, Int, Int>(){

    // params -> doInBackround 에서 사용할 타입
    // progress -> onProgressUpdate 에서 사용할 타입
    // result -> onPostExecute 에서 사용할 타입
    var percent : Int = 0

    override fun onPreExecute() { // 작업 시작
        percent = 0
        progressBar.setProgress(percent)
    }

    override fun doInBackground(vararg params: Int?): Int {  //vararg = Int 여러 개 오는 것
        while(isCancelled() == false){  //내 asynctask 가 완료되기 전 멈추면 true
            percent++
            if(percent > 100){
                break
            }else{
                publishProgress(percent) // onProgressUpdate에 전달 -> onProgressUpdate의 values에 전달!
            }

            // 변화되는 모습 보기 위해 지연 설정
            try{
                Thread.sleep(100)
            }catch(e : Exception){
                e.printStackTrace()
            }
        }
        return percent  // 100퍼센트 되었을 때 100 return!
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.setProgress(values[0] ?: 0)  //null 인 경우에는 0!
        progressText.setText("퍼센트 : " + values[0] + "%")
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        progressText.setText("작업이 완료되었습니다")
    }

    // 취소 시
    override fun onCancelled() {
        progressBar.setProgress(0)
        progressText.setText("작업이 취소되었습니다")
    }


}
