package com.beyondworlds.wanandroid.adapter

import android.text.TextUtils
import com.beyondworlds.wanandroid.R
import com.beyondworlds.wanandroid.net.bean.Artical
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *  Created by BeyondWorlds
 *  on 2020/6/10
 */
class HomeArticalAdapter(data: MutableList<Artical>) :
    BaseQuickAdapter<Artical, BaseViewHolder>(R.layout.item_home_article, data) {
    init {
        addChildClickViewIds(R.id.iv_like)
    }

    override fun convert(holder: BaseViewHolder, item: Artical) {

        if (TextUtils.isEmpty(item.author)) {
            holder.setText(R.id.tv_author, context.getString(R.string.share_user) + item.shareUser)
        } else {
            holder.setText(R.id.tv_author, context.getString(R.string.author) + item.author)
        }

        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_date, "" + item.niceShareDate)
        holder.setText(
            R.id.tv_type,
            "${context.getString(R.string.type)}${item.superChapterName}/${item.chapterName}"
        )

        if (item.collect) {
            holder.setImageResource(R.id.iv_like, R.drawable.ic_favorite_red_24dp)
        } else {
            holder.setImageResource(R.id.iv_like, R.drawable.ic_favorite_border_black_24dp)
        }
    }
}