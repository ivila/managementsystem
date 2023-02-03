<template>
  <div style="padding: 10px">
    <el-form ref="form" :inline="true" label-width="0" :model="data" :rules="rules" @submit.prevent>
      <el-form-item prop="author">
        <el-input v-model="data.author" placeholder="please input author" clearable  @keyup.enter="doSearch">
          <template #prepend>Author</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="isbn">
        <el-input v-model="data.isbn" placeholder="please input isbn" clearable  @keyup.enter="doSearch">
          <template #prepend>ISBN</template>
        </el-input>
      </el-form-item>
      <el-form-item prop="operator">
        <el-input v-model="data.operator" placeholder="please input operator" clearable  @keyup.enter="doSearch">
          <template #prepend>Operator</template>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" v-loading="loading" :icon="Search" @click="doSearch">Search</el-button>
        <el-button type="primary" :icon="DocumentAdd" @click="doEdit(0)">Create</el-button>
      </el-form-item>
    </el-form>
    <el-table key="book_list" :data="list" :height="tableHeight" style="width: 100%">
      <el-table-column prop="id" label="ID" max-width="60" show-overflow-tooltips/>
      <el-table-column prop="isbn" label="ISBN" max-width="100" show-overflow-tooltips/>
      <el-table-column prop="bookName" label="书名" max-width="150" show-overflow-tooltips/>
      <el-table-column label="封面">
        <template #default="scope">
          <el-popover width="200" placement="right">
            <template #reference>
              <el-button type="text">Link</el-button>
            </template>
            <img :src="scope.row.coverUrl" width="180"/>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column prop="author" label="作者" max-width="80" show-overflow-tooltips/>
      <el-table-column prop="operator" label="最后操作人" max-width="80" show-overflow-tooltips/>
      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button :icon="Edit" type="primary" circle @click="doEdit(scope.row.id)"/>
          <el-popconfirm title="Are you sure to delete this?" @confirm="doDelete(scope.row.id)">
            <template #reference>
              <el-button :icon="Delete" type="danger" circle/>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-row style="width: 100%; padding-top: 20px" justify="end">
      <el-pagination
        v-model:current-page="data.page"
        v-model:page-size="data.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="count"
        @size-change="doSearch"
        @current-change="doSearch"
      />
    </el-row>
    <book-edit :input-value="editID" v-model="editVisible" @close="editID = 0" @success="doSearch"/>
  </div>
</template>

<script>
import { Search, DocumentAdd, Edit, Delete } from '@element-plus/icons-vue'
import { checkFormValidate, toggleParam } from '@/libs/decorator.js'
import { List, DoDelete } from '@/apis/books.js'
import BookEdit from './edit.vue'

export default {
  name: 'book-list',
  components: {
    'book-edit': BookEdit
  },
  data () {
    return {
      Search,
      DocumentAdd,
      Edit,
      Delete,
      loading: false,
      count: 0,
      list: [],
      editVisible: false,
      editID: 0,
      data: {
        operator: '',
        isbn: '',
        author: '',
        page: 1,
        pageSize: 20,
      },
      result: null,
      rules: {
      }
    }
  },
  mounted() {
    this.doSearch()
  },
  computed: {
    tableHeight() {
      return this.$globalVar.WindowHeight - 250
    }
  },
  methods: {
    @toggleParam('loading')
    @checkFormValidate('form')
    async doSearch () {
      let result = await List(this.data)
      this.count = result.count
      this.list = result.list
    },
    doEdit(id) {
      this.editID = id
      this.editVisible = true
    },
    async doDelete(id) {
      await DoDelete({'id': id})
      this.doSearch()
    }
  }
}
</script>
