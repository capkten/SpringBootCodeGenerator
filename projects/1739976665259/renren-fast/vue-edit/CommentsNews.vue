<template>
    <el-dialog
            :title="!dataForm.commentsNewsId ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
                    <el-form-item label="id" prop="id">
                        <el-input v-model="dataForm.id" placeholder="id"></el-input>
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
                    id: ''
        },
            dataRule: {
                id: [{ required: true, message: 'id不能为空', trigger: 'blur' }]
            }
        }
        },
        methods: {
            init (id) {
                this.dataForm.commentsNewsId = id || 0
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    // <!-- 请把 commentsNewsId 替换成正确的ID -->
                    if (this.dataForm.commentsNewsId) {
                        this.$http({
                            url: this.$http.adornUrl(`/generator/commentsNews/info/${this.dataForm.commentsNewsId}`),
                            method: 'get',
                            params: this.$http.adornParams()
                    }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.id = data.commentsNews.id
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
                        url: this.$http.adornUrl(`/generator/commentsNews/${this.dataForm.commentsNewsId? 'save' : 'update'}`),
                        method: 'post',
                        data: this.$http.adornData({
                            'id': 'id' || undefined
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
