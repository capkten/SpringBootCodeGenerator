<template>
    <el-dialog
            :title="!dataForm.reportCommentsPostsId ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
                    <el-form-item label="cp_id" prop="cpId">
                        <el-input v-model="dataForm.cpId" placeholder="cp_id"></el-input>
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
                    cpId: ''
        },
            dataRule: {
                cpId: [{ required: true, message: 'cp_id不能为空', trigger: 'blur' }]
            }
        }
        },
        methods: {
            init (id) {
                this.dataForm.reportCommentsPostsId = id || 0
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    // <!-- 请把 reportCommentsPostsId 替换成正确的ID -->
                    if (this.dataForm.reportCommentsPostsId) {
                        this.$http({
                            url: this.$http.adornUrl(`/generator/reportCommentsPosts/info/${this.dataForm.reportCommentsPostsId}`),
                            method: 'get',
                            params: this.$http.adornParams()
                    }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.cpId = data.reportCommentsPosts.cpId
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
                        url: this.$http.adornUrl(`/generator/reportCommentsPosts/${this.dataForm.reportCommentsPostsId? 'save' : 'update'}`),
                        method: 'post',
                        data: this.$http.adornData({
                            'cpId': 'cpId' || undefined
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
