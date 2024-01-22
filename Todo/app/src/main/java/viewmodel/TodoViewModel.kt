package viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel:ViewModel() {
    var todos = mutableStateListOf<Todo>()
        private set
    init {
        getTodoList()
    }

    private fun getTodoList(){
        viewModelScope.launch {
            val todosApi:TodosApi?
            try{
                todosApi = TodosApi.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e:Exception){
                Log.d("OPENMODELICA",e.message.toString())
            }
        }
    }
}