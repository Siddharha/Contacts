package `in`.creativelizard.contacts.utils

import `in`.creativelizard.contacts.R
import android.content.Context
import android.graphics.*
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper

class AppUtil {
    companion object{
        fun getBitmapFromDrawable(context: Context, @DrawableRes vectorDrawableResourceId: Int): Bitmap {
            val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
            vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            vectorDrawable.draw(canvas)
            return bitmap
        }

         fun initSwipe(rlItem: androidx.recyclerview.widget.RecyclerView?,p:Paint,context: Context) {
            val simpleItemTouchCallback =
                object :
                    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                    override fun onMove(
                        recyclerView: androidx.recyclerview.widget.RecyclerView,
                        viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
                        target: androidx.recyclerview.widget.RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(
                        viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
                        direction: Int
                    ) {
                        val position = viewHolder.adapterPosition

                        if (direction == ItemTouchHelper.LEFT) {
                            // showDeletePopup(position, rlItem?.childCount!!)

                        } else {

                            /* edit_position = position
                             val alertDialog = AlertDialog.Builder(activity!!)
                             alertDialog.setCancelable(false)
                                 .setMessage("Do you want to Edit the Item?")
                                 .setPositiveButton("Yes") { d, _ ->
                                     // Toast.makeText(activity!!,"Not Implemented!",Toast.LENGTH_SHORT).show()
                                     confirmItemListAdapter?.notifyItemChanged(position)
                                     val bundle = Bundle()
                                     bundle.putString("category", "Edit Product")
                                     val itemString = Gson().toJson(itemList?.get(position))
                                     Log.e("data", itemString)
                                     bundle.putString(
                                         "PickupId",
                                         itemList?.get(position)?.qrCode?.split("-")!![1]
                                     )
                                     bundle.putString("product_data", itemString)
                                     bundle.putString("booking_id", arguments?.getString("booking_id"))
                                     bundle.putString("company_info", insuranceAndCompanyInfo)
                                     *//*startActivity(Intent(activity!!,EditAndAddProductFragment::class.java))*//*
                                Navigation.findNavController(rootView!!).navigate(
                                    R.id.action_confirmOrderFragment_to_editAndAddProductFragment,
                                    bundle
                                )
                                d.dismiss()
                            }
                            .setNegativeButton("No") { d, _ ->
                                confirmItemListAdapter?.notifyItemChanged(position)
                                d.dismiss()
                            }
                            .create().show()*/
                        }
                    }

                    override fun onChildDraw(
                        c: Canvas,
                        recyclerView: androidx.recyclerview.widget.RecyclerView,
                        viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
                        dX: Float,
                        dY: Float,
                        actionState: Int,
                        isCurrentlyActive: Boolean
                    ) {

                        val icon: Bitmap
                        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                            val itemView = viewHolder.itemView
                            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                            val width = height / 3

                            if (dX > 0) {
                                p.color = Color.parseColor("#3F51B5")
                                val background = RectF(
                                    itemView.left.toFloat(),
                                    itemView.top.toFloat(), /*dX*/
                                    viewHolder.itemView.width.toFloat(),
                                    itemView.bottom.toFloat()
                                )
                                c.drawRoundRect(background, 90f, 90f, p)
                                icon =
                                    AppUtil.getBitmapFromDrawable(context, R.drawable.ic_edit_white)
                                val icon_dest = RectF(
                                    itemView.left.toFloat() + width,
                                    itemView.top.toFloat() + width,
                                    itemView.left.toFloat() + 2 * width,
                                    itemView.bottom.toFloat() - width
                                )
                                c.drawBitmap(icon, null, icon_dest, p)
                                /* p.color = Color.WHITE
                                 p.textSize = 35f
                                 c.drawText("Edit Product",(itemView.width/2).toFloat()-50,(itemView.height/2).toFloat()+10,p)*/
                            } else {
                                p.color = Color.parseColor("#3F3F3F")
                                val background = RectF(
                                    itemView.right.toFloat() /*+ dX*/ - viewHolder.itemView.width.toFloat(),
                                    itemView.top.toFloat(),
                                    itemView.right.toFloat(),
                                    itemView.bottom.toFloat()
                                )
                                c.drawRoundRect(background, 90f, 90f, p)
                                icon = AppUtil.getBitmapFromDrawable(
                                    context,
                                    R.drawable.ic_delete_white
                                )
                                val icon_dest = RectF(
                                    itemView.right.toFloat() - 2 * width,
                                    itemView.top.toFloat() + width,
                                    itemView.right.toFloat() - width,
                                    itemView.bottom.toFloat() - width
                                )
                                c.drawBitmap(icon, null, icon_dest, p)
                                /* p.color = Color.WHITE
                                 p.textSize = 35f
                                 c.drawText("Delete Product",(itemView.width/2).toFloat()-70,(itemView.height/2).toFloat()+10,p)*/
                            }
                        }
                        super.onChildDraw(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                    }

                }

            val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
            itemTouchHelper.attachToRecyclerView(rlItem)
        }
    }
}