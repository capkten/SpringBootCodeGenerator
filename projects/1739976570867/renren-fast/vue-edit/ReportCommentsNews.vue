<template>
    <el-dialog
            :title="!dataForm.reportCommentsNewsId ? '新增' : '修改'"
            :close-on-click-modal="false"
            :visible.sync="visible">
        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
                    <el-form-item label="cn_id" prop="cnId">
                        <el-input v-model="dataForm.cnId" placeholder="cn_id"></el-input>
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
                    cnId: ''
        },
            dataRule: {
                cnId: [{ required: true, message: 'cn_id不能为空', trigger: 'blur' }]
            }
        }
        },
        methods: {
            init (id) {
                this.dataForm.reportCommentsNewsId = id || 0
                this.visible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].resetFields()
                    // <!-- 请把 reportCommentsNewsId 替换成正确的ID -->
                    if (this.dataForm.reportCommentsNewsId) {
                        this.$http({
                            url: this.$http.adornUrl(`/generator/reportCommentsNews/info/${this.dataForm.reportCommentsNewsId}`),
                            method: 'get',
                            params: this.$http.adornParams()
                    }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.dataForm.cnId = data.reportCommentsNews.cnId
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
                        url: this.$http.adornUrl(`/generator/reportCommentsNews/${this.dataForm.reportCommentsNewsId? 'save' : 'update'}`),
                        method: 'post',
                        data: this.$http.adornData({
                            'cnId': 'cnId' || undefined
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
