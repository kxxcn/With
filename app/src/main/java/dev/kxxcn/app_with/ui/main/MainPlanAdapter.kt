package dev.kxxcn.app_with.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import dev.kxxcn.app_with.R
import dev.kxxcn.app_with.data.model.plan.Plan
import dev.kxxcn.app_with.util.ImageProcessingHelper
import java.lang.ref.WeakReference
import java.util.*

class MainPlanAdapter(context: Context?) : RecyclerView.Adapter<MainPlanAdapter.ViewHolder>() {

    private var mContext: Context? = context

    private val resourcesOfMonth = intArrayOf(R.drawable.ic_january, R.drawable.ic_february, R.drawable.ic_march,
            R.drawable.ic_april, R.drawable.ic_may, R.drawable.ic_june, R.drawable.ic_july, R.drawable.ic_august,
            R.drawable.ic_september, R.drawable.ic_october, R.drawable.ic_november, R.drawable.ic_december)

    private var mPlanList: List<Plan>? = null

    private val option = RequestOptions()

    private var refRecyclerView: WeakReference<RecyclerView>? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        refRecyclerView = WeakReference(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        refRecyclerView = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_plan, parent, false))

    override fun getItemCount() = if (mPlanList == null) 0 else mPlanList!!.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val date = mPlanList?.get(position)?.date?.split(REGEX_DATE)
            setBackgroundMonthTextView(Integer.parseInt(date?.get(1)), holder.monthIv)
            holder.planTv.text = mPlanList?.get(position)?.plan
            holder.dateTimeTv.text = String.format(mContext!!.getString(R.string.text_plan_date_time),
                    mPlanList?.get(position)?.date, mPlanList?.get(position)?.time)
            holder.placeTv.text = mPlanList?.get(position)?.place
            if (date != null) {
                holder.decimalDayTv.text = getDDay(date[0].toInt(), date[1].toInt() - 1, date[2].toInt())
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }

    fun setItems(planList: List<Plan>) {
        mPlanList = planList
        notifyDataSetChanged()
        val recyclerView = refRecyclerView?.get()
        recyclerView?.scheduleLayoutAnimation()
    }

    private fun setBackgroundMonthTextView(month: Int, monthIv: ImageView) {
        ImageProcessingHelper.setGlide(mContext, resourcesOfMonth[month - 1], monthIv, option)
    }

    private fun getDDay(year: Int, month: Int, day: Int): String {
        val today = Calendar.getInstance()
        val decimalDay = Calendar.getInstance()
        decimalDay.set(year, month, day)
        val longDecimalDay = decimalDay.timeInMillis / (24 * 60 * 60 * 1000)
        val longToday = today.timeInMillis / (24 * 60 * 60 * 1000)

        val count = (longToday - longDecimalDay).toInt()
        if (count > 0) {
            return String.format(mContext?.getString(R.string.text_decimal_day_plus).toString(), count)
        } else if (count == 0) {
            return mContext?.getString(R.string.text_decimal_day).toString()
        }

        return String.format(mContext?.getString(R.string.text_decimal_day_minus).toString(), count)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val monthIv: ImageView = itemView.findViewById(R.id.iv_month)
        val planTv: TextView = itemView.findViewById(R.id.tv_plan)
        val dateTimeTv: TextView = itemView.findViewById(R.id.tv_date_time)
        val placeTv: TextView = itemView.findViewById(R.id.tv_place)
        val decimalDayTv: TextView = itemView.findViewById(R.id.tv_decimal_day)
    }

    companion object {
        private const val REGEX_DATE = "-"
    }
}