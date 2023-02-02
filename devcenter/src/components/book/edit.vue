<template>
  <el-drawer
    v-loading="dataLoading"
    element-loading-text="data loading, please wait..."
    v-model="value"
    :title="title"
    direction="rtl">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="BookName" prop="bookName">
        <el-input v-model="form.bookName"/>
      </el-form-item>
      <el-form-item label="Author" prop="author">
        <el-input v-model="form.author"/>
      </el-form-item>
      <el-form-item label="Cover" prop="coverUrl">
        <el-input v-model="form.coverUrl"/>
      </el-form-item>
      <el-form-item label="ISBN" prop="isbn">
        <el-input v-model="form.isbn"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="close">Cancel</el-button>
        <el-button type="primary" @click="save" v-loading="saving">Confirm</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script>
import { checkFormValidate, toggleParam } from '@/libs/decorator.js'
import { Get, Create, Update } from '@/apis/books.js'

function getDefaultValue() {
  return {
    id: 0,
    operator: '',
    coverUrl: '',
    bookName: '',
    author: '',
  }
}

export default {
  name: 'book-edit',
  props: {
    modelValue: {
      type: Boolean
    },
    inputValue: {
      type: Number
    },
  },
  watch: {
    inputValue (newVal) {
      if (!newVal) {
        this.form = getDefaultValue()
        return
      }
      this.initForEdit()
    }
  },
  data () {
    return {
      dataLoading: false,
      saving: false,
      form: getDefaultValue(),
      rules: {
        bookName: [{ required: true, max: 255, trigger: 'blur' }],
        isbn: [{ required: true, max: 32, trigger: 'blur' }],
        coverUrl: [{ required: true, max: 255, trigger: 'blur' }],
        author: [{ required: true, max: 255, trigger: 'blur' }],
      },
    }
  },
  computed: {
    title() {
      return this.EditId ? `[Update]Book-${this.EditId}` : '[Create]Book'
    },
    value: {
      get() {
        return this.modelValue
      },
      set(newVal) {
        this.$emit('update:modelValue', newVal)
      }
    },
    EditId() {
      return this.inputValue
    }
  },
  methods: {
    @toggleParam('dataLoading')
    async initForEdit() {
      let result = await Get({'id': this.EditId})
      this.form = {
        id: this.EditId,
        coverUrl: result.coverUrl,
        bookName: result.bookName,
        author: result.author,
        isbn: result.isbn,
      }
    },
    close() {
      this.$emit('update:modelValue', false)
      this.$emit('close')
    },
    @checkFormValidate('form')
    @toggleParam('saving')
    async save() {
      let method = this.form.id ? Update : Create
      await method(this.form)
      this.$message.success('Operation Success')
      this.close()
      this.$emit('success')
    }
  }
}
</script>
