import axios from 'axios'
import ElementUI from 'element-ui';

const service=axios.create({
    baseURL: 'http://localhost:8090',//同意请求前缀
    timeout: 50000,
})
//设置请求拦截器，post请求内容类型
service.defaults.headers.post['Content-Type']='application/json';
service.interceptors.request.use(config =>{
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    if(localStorage.getItem("token")!=null && localStorage.getItem("token")!=''){
        //设置token
        config.headers['token'] = localStorage.getItem("token");
    }
    return config
},error => {
    return Promise.reject(error)
});
//设置响应拦截器
//可以在后台响应后同意处理结果
service.interceptors.response.use(response =>{
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        //当权限验证失败给出提示
        if(res.code==='401'){
            ElementUI.Message({
                message:res.msg,
                type:'error'
            })
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    })

export default service