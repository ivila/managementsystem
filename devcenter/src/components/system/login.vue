<template>
  <!--TODO 我们需要一张背景图 -->
  <div class="login" align="center">
    <el-card align="center" class="login-card">
      <h2>Login</h2>
      <el-form ref="form" :model="data" :rules="rules">
        <el-form-item prop="email">
          <el-input
            v-model="data.email"
            placeholder="Email"
            :prefix-icon="User"
            @keyup.enter="doLogin">
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="data.password"
            placeholder="Password"
            type="password"
            :prefix-icon="Lock"
            @keyup.enter="doLogin">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%;" :loading="loading" @click="doLogin">Login</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import md5 from 'blueimp-md5'
import { Login } from '@/apis/auth.js'
import { APIError } from '@/libs/error.js'
import { ErrCode } from '@/libs/constant.js'
import { checkFormValidate, toggleParam } from '@/libs/decorator.js'
import { User, Lock } from '@element-plus/icons-vue'

export default {
  name: "login",
  data() {
    return {
      data: {
        User,
        Lock,
        email: '',
        password: ''
      },
      loading: false,
      rules: {
        email: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur' }],
      },
      redirect: null
    };
  },
  watch: {
    $route: {
      handler: function (val) {
        const query = val.query
        console.log('query', val)
        if (query) {
          this.redirect = query.redirect
        }
      },
      immediate: true
    }
  },
  methods: {
    @toggleParam('loading')
    @checkFormValidate('form')
    async doLogin () {
      try {
        let data = {
          email: this.data.email,
          password: md5(this.data.password),
        }
        let resp = await Login(data)
        this.$store.commit('user/SET_INFO', {
          email: resp['email'],
          name: resp['userName']
        })
        this.$router.push({ path: this.redirect || '/' })
      } catch (err) {
        if ((err instanceof APIError) &&
          (err['retcode'] === ErrCode.LOGIN_FAILED)) {
          this.$message.warning('Wrong Username or Password')
          return
        }
        throw err
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  background: var(--el-border-color-lighter);
  height: 100vh;
  vertical-align: center;
}

.login-card {
  width: 400px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
