<template>
    <el-dialog
            :title="!dataForm.reportPostsId ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
                    <el-form-item label="p_id" prop="pId">
                        <el-input v-model="dataForm.pId" placeholder="p_id"></el-input>
                    </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
    </el-dialog>
</template>

<script>
    export default {
        data () {
            return {
                visible: false,
                dataForm: {
                    pId: ''
        },
            dataRule: {
                pId: [{ required: true, message: 'p_id不能为空', trigger: 'blur' }]
            }
        }
        },
        methods: {
            init (id) {
                this.dataForm.reportPostsId = id || 0
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    // <!-- 请把 reportPostsId 替换成正确的ID -->
                    if (this.dataForm.reportPostsId) {
                        this.$http({
                            url: this.$http.adornUrl(`/generator/reportPosts/info/${this.dataForm.reportPostsId}`),
                            method: 'get',
                            params: this.$http.adornParams()
                    }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.pId = data.reportPosts.pId
                            }
                        })
                    }
                })
            },
            // 表单提交
            dataFormSubmit () {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                    this.$http({
                        url: this.$http.adornUrl(`/generator/reportPosts/${this.dataForm.reportPostsId? 'save' : 'update'}`),
                        method: 'post',
                        data: this.$http.adornData({
                            'pId': 'pId' || undefined
                        })
                }).then(({data}) => {
                        if (data && data.code === 0) {
                            this.$message({
                                message: '操作成功',
                                type: 'success',
                                duration: 1500,
                                onClose: () => {
                                    this.visible = false
                                    this.$emit('refreshDataList')
                                }
                        })
                        } else {
                            this.$message.error(data.msg)
                        }
                    })
                }
                })
            }
        }
    }
</script>
