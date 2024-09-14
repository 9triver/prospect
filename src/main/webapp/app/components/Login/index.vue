<template>
  <div class="login-page">
    <div class="login-page-wrapper">
      <div class="title-wrapper">
        <h2>项目管理系统</h2>
      </div>
      <el-form label-position="top" :rules="rules" :model="formData" ref="loginForm" class="login-form-wrapper">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model.trim="formData.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model.trim="formData.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="handleSubmit">立即登录</el-button>
          <el-checkbox v-model="formData.rememberMe" @change="!formData.rememberMe">下次自动登录</el-checkbox>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, inject} from 'vue'
import { useRouter } from 'vue-router';
import axios from 'axios';

// 路由
const router = useRouter();

// 表单
const loginForm = ref(null)
// 表单数据
const formData = reactive({
  username:'',
  password:'',
  rememberMe:false
})
// 表单校验数据
const rules = reactive({
  username: [
    { required: 'true', message: '账户不能为空', trigger: 'blur' }
  ],
  password: [
    { required: 'true', message: '密码不能为空', trigger: 'blur' }
  ]
})

const accountService = inject('accountService');
const loginService = inject('loginService');
const {setshowIndex} = inject('showIndex')
// 处理登录逻辑
async function handleSubmit(){
      try {
        const result = await axios.post('api/authenticate', formData);
        const bearerToken = result.headers.authorization;
        if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
          const jwt = bearerToken.slice(7, bearerToken.length);
          if (formData.rememberMe) {
            localStorage.setItem('jhi-authenticationToken', jwt);
            sessionStorage.removeItem('jhi-authenticationToken');
          } else {
            sessionStorage.setItem('jhi-authenticationToken', jwt);
            localStorage.removeItem('jhi-authenticationToken');
          }
        }
        loginService.hideLogin();
        await accountService.retrieveAccount();
        if(result.status == '200'){
          router.push('/home')
          setshowIndex(true)
        }
      } catch (_error) {
        console.error(_error)
      }
}

</script>

<style scoped>
  .login-page {
    display: flex;
    justify-content: end;
    align-items: center;
    width: 100%;
    height: 100vh;
    background-image: url('./asset/login-bg.png');
    background-size: 100% 100%;
  }
  .login-page-wrapper {
    width: 420px;
    height: 500px;
    background-color: #fff;
    border-radius: 4px;
    box-shadow: 0px 21px 41px 0px rgba(0, 0, 0, 0.2);
    margin-right: 200px;
  }
  .title-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 40px 0 20px 0;
  }
  .login-form-wrapper {
    width: 70%;
    margin: 0 auto;
  }
  .login-form-wrapper >>> .el-form--label-top .el-form-item__label {
    padding: 0;
  }
  .login-form-wrapper >>> .el-form-item {
    margin-bottom: 15px 0px;
  }
</style>