package io.untaek.animal_new.list.mypage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import io.untaek.animal_new.activity.postdetail.PostDetailActivity
import io.untaek.animal_new.databinding.ItemListMypageBinding
import io.untaek.animal_new.type.Post
import io.untaek.animal_new.type.User
import io.untaek.animal_new.viewmodel.MyPageViewModel

class MyPageAdapter (fragmentActivity: FragmentActivity) : RecyclerView.Adapter<MyPageAdapter.ViewHolder>(){

    companion object {
        const val FROM = "from"

        const val FROM_UNKNOWN = -1
        const val FROM_CONTENT = 0
        const val FROM_COMMENT = 1
        const val FROM_NOTIFICATION = 2
    }
    val vm = ViewModelProviders.of(fragmentActivity).get(MyPageViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageAdapter.ViewHolder {
        val binding = ItemListMypageBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            handler = Handler(vm)
        }
        return ViewHolder(binding)
    }

    override fun getItemCount (): Int {
        Log.e("ㅋㅋㅋ", "MyPageAdapter : postList.size = "+ vm.postList.size)
        return vm.postList.size
    }

    override fun onBindViewHolder(holder: MyPageAdapter.ViewHolder, position: Int) {
        holder.bind(vm.postList[position])
    }


    open class ViewHolder(val binding: ItemListMypageBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){
            binding.post = post
        }
    }

    class Handler(val vm: MyPageViewModel){

        fun onClickLike(view: View, post: Post) {
            Toast.makeText(view.context, "onClickLike", Toast.LENGTH_SHORT).show()
        }
        fun onClickContent(view: View, post: Post) {
            Toast.makeText(view.context, "onClickContent", Toast.LENGTH_SHORT).show()
            val intent = Intent(view.context, PostDetailActivity::class.java).apply {
                putExtra("post", post)
                putExtra(PostDetailActivity.FROM, PostDetailActivity.FROM_CONTENT)
                Log.d("ㅋㅋㅋ", "mypage - content click ")
            }
            view.context.startActivity(intent)
        }
        fun onClickComment(view: View, post: Post) {
            Toast.makeText(view.context, "onClickComment", Toast.LENGTH_SHORT).show()
            val intent = Intent(view.context, PostDetailActivity::class.java).apply {
                putExtra("post", post)
                putExtra(PostDetailActivity.FROM, PostDetailActivity.FROM_COMMENT)
                Log.d("ㅋㅋㅋ", "mypage - comment click ")
            }
            view.context.startActivity(intent)
        }
    }
}