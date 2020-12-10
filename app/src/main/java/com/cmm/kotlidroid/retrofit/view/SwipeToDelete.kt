package com.cmm.kotlidroid.retrofit.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.cmm.kotlidroid.retrofit.viewmodel.PokemonViewModel


class SwipeToDelete(
    private var adapter: PokedexAdapter,
    private var pokemonViewModel: PokemonViewModel
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val background = ColorDrawable(Color.RED)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        val itemToBeDeleted = adapter.getItemAt(pos)

        adapter.deleteItem(pos)
        pokemonViewModel.delete(itemToBeDeleted)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val itemView: View = viewHolder.itemView
        val offset = 20

        //Swipe left
        if (dX < 0) {
            background.setBounds(
                itemView.right + dX.toInt() - offset,
                itemView.top, itemView.right, itemView.bottom
            )
        } else {
            background.setBounds(0, 0, 0, 0);
        }

        background.draw(c)
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}