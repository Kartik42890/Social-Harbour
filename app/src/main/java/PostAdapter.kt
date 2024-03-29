
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialharbour.Models.Post
import com.example.socialharbour.Models.User
import com.example.socialharbour.R
import com.example.socialharbour.databinding.PostRvBinding
import com.example.socialharbour.utils.USER_NODE
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class PostAdapter(var context: Context,var postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.MyHolder>(){


    inner class MyHolder(var binding : PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        try {
            Firebase.firestore.collection(USER_NODE).document(postList.get(position).uid).get().addOnSuccessListener {

                var user = it.toObject<User>()
                Glide.with(context).load(user!!.image).placeholder(R.drawable.profile).into(holder.binding.profileImage)
                holder.binding.name.text = user.name
            }

        }catch (e:Exception){

        }
        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
        holder.binding.caption.text=postList.get(position).caption

        holder.binding.share.setOnClickListener {
            shareContent()
        }


                holder.binding.like.setOnClickListener {
                    holder.binding.like.setImageResource(R.drawable.heart_like)
                }

    }

    private fun shareContent() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Check out this awesome content!")
        }

        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}